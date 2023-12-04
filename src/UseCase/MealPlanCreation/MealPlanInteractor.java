package UseCase.MealPlanCreation;

import Entities.MealPlan;
import Entities.MealPlanDay;
import Entities.Recipe;

import java.io.IOException;
import java.util.List;
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
        String diet = mealPlanInputData.getDiet();
        int calorieLimit  = mealPlanInputData.getCalorieLimit();
        List<Recipe> filteredRecipes = null;
        try {
            filteredRecipes = mealPlanAPIDataAccessObject.findRecipes(diet, calorieLimit);
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
        MealPlan mealPlan = new MealPlan(startDate.toString(), endDate.toString(), mealPlanInputData.getDiet(), mealPlanInputData.getCalorieLimit());

        for (int dayIndex = 0; dayIndex < duration; dayIndex++) {
            Recipe breakfast = selectRecipeByMealType(filteredRecipes, "breakfast");
            filteredRecipes.remove(breakfast);
            Recipe lunch = selectRecipeByMealType(filteredRecipes, "lunch");
            filteredRecipes.remove(lunch);
            Recipe dinner = selectRecipeByMealType(filteredRecipes, "dinner");
            filteredRecipes.remove(dinner);

            if (breakfast == null){
               breakfast = filteredRecipes.get(0);
                filteredRecipes.remove(breakfast);
            }
            if (lunch == null){
                lunch = filteredRecipes.get(0);
                filteredRecipes.remove(lunch);
            }
            if (dinner == null){
                dinner = filteredRecipes.get(0);
                filteredRecipes.remove(dinner);
            }

            MealPlanDay mealPlanDay = new MealPlanDay(breakfast, lunch, dinner);
            mealPlan.addMealPlanDay(mealPlanDay);
        }

        return mealPlan;
    }

    private Recipe selectRecipeByMealType(List<Recipe> recipes, String mealType) {
        for (Recipe recipe : recipes) {
            if (recipe.getMealType().contains(mealType)) {
                return recipe;
            }
        }
        return null;
    }
    @Override
    public void back(){
        mealPlanPresenter.back();
    }

}
