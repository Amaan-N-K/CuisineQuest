package UseCase.MealPlanCreation;

import Entities.MealPlan;
import Entities.MealPlanDay;
import Entities.MealPlanFactory;
import Entities.Recipe;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class MealPlanInteractor implements MealPlanInputBoundary{
    private final MealPlanAPIDataAccessInterface mealPlanAPIDataAccessObject;
    private final MealPlanOutputBoundary mealPlanPresenter;

    public MealPlanInteractor(MealPlanAPIDataAccessInterface mealPlanAPIDataAccessInterface,
                              MealPlanOutputBoundary mealPlanOutputBoundary) {
        this.mealPlanAPIDataAccessObject = mealPlanAPIDataAccessInterface;
        this.mealPlanPresenter = mealPlanOutputBoundary;
    }
    @Override
    public void createMealPlan(MealPlanInputData mealPlanInputData) {
        LocalDate startDate = LocalDate.parse(mealPlanInputData.getStartDate());
        LocalDate endDate = LocalDate.parse(mealPlanInputData.getEndDate());
        if (!isValidDateRange(startDate, endDate)) {
            mealPlanPresenter.prepareFailView("Invalid dates.");
            return;
        }
        String diets = mealPlanInputData.getDiets();
        int calorieLimit  = mealPlanInputData.getCalorieLimit();
        List<Recipe> filteredRecipes = null;
        try {
            filteredRecipes = mealPlanAPIDataAccessObject.findRecipes(diets, calorieLimit);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MealPlan mealPlan = generateMealPlan(startDate, endDate, filteredRecipes, mealPlanInputData);

        if (mealPlan != null) {
            MealPlanOutputData mealPlanOutputData = new MealPlanOutputData(mealPlan);
            mealPlanPresenter.presentMealPlan(mealPlanOutputData);
        } else {
            mealPlanPresenter.prepareFailView("Unable to create a meal plan with the given criteria.");
        }
    }

    private boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        return !startDate.isAfter(endDate);
    }

    private MealPlan generateMealPlan(
            LocalDate startDate,
            LocalDate endDate,
            List<Recipe> filteredRecipes,
            MealPlanInputData mealPlanInputData
    ) {
        int duration = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        MealPlan mealPlan = new MealPlan(startDate.toString(), endDate.toString(), mealPlanInputData.getDiets(), mealPlanInputData.getCalorieLimit());

        for (int dayIndex = 0; dayIndex < duration; dayIndex++) {
            Recipe breakfast = selectRecipeByMealType(filteredRecipes, "breakfast");
            Recipe lunch = selectRecipeByMealType(filteredRecipes, "lunch");
            Recipe dinner = selectRecipeByMealType(filteredRecipes, "dinner");

            if (breakfast == null || lunch == null || dinner == null) {
                return null;
            }

            MealPlanDay mealPlanDay = new MealPlanDay(breakfast, lunch, dinner);
            mealPlan.addMealPlanDay(mealPlanDay);
        }

        return mealPlan;
    }

    private Recipe selectRecipeByMealType(List<Recipe> recipes, String mealType) {
        for (Recipe recipe : recipes) {
            if (recipe.getMealType().equals(mealType)) {
                return recipe;
            }
        }
        return null;
    }

}
