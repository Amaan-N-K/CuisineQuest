package use_case.meal_plan_creation;

import entities.MealPlan;
import entities.MealPlanDay;
import entities.Recipe;
import data_access.UserDataAccessObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MealPlanInteractor implements MealPlanInputBoundary{
    private final MealPlanAPIDataAccessInterface mealPlanAPIDataAccessObject;
    private final MealPlanOutputBoundary mealPlanPresenter;

    private final MealPlanDataAccessInterface mealPlanDataAccessInterface;

    private final UserDataAccessObject userDataAccessObject;

    public MealPlanInteractor(MealPlanAPIDataAccessInterface mealPlanAPIDataAccessInterface,
                              MealPlanOutputBoundary mealPlanOutputBoundary,
                              MealPlanDataAccessInterface mealPlanDataAccessInterface,
                              UserDataAccessObject userDataAccessObject) {
        this.mealPlanAPIDataAccessObject = mealPlanAPIDataAccessInterface;
        this.mealPlanPresenter = mealPlanOutputBoundary;
        this.mealPlanDataAccessInterface = mealPlanDataAccessInterface;
        this.userDataAccessObject = userDataAccessObject;
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
            String currUser = userDataAccessObject.getActive();
            System.out.println(currUser);
            try {
                mealPlanDataAccessInterface.saveMealPlan(currUser, mealPlan);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            List<MealPlanDay> mealPlanDays = mealPlan.getMealPlanDays();
            List<String> breakfastNames = new ArrayList<>();
            List<String> breakfastDescriptions = new ArrayList<>();
            List<String> lunchNames = new ArrayList<>();
            List<String> lunchDescriptions = new ArrayList<>();
            List<String> dinnerNames = new ArrayList<>();
            List<String> dinnerDescriptions = new ArrayList<>();

            for (MealPlanDay day : mealPlanDays) {
                Recipe breakfast = day.getBreakfastRecipe();
                Recipe lunch = day.getLunchRecipe();
                Recipe dinner = day.getDinnerRecipe();

                if (breakfast != null) {
                    breakfastNames.add(breakfast.getName());
                    breakfastDescriptions.add(breakfast.getDescription());
                }
                if (lunch != null) {
                    lunchNames.add(lunch.getName());
                    lunchDescriptions.add(lunch.getDescription());
                }
                if (dinner != null) {
                    dinnerNames.add(dinner.getName());
                    dinnerDescriptions.add(dinner.getDescription());
                }
            }
            MealPlanOutputData mealPlanOutputData = new MealPlanOutputData(breakfastNames, breakfastDescriptions, lunchNames, lunchDescriptions, dinnerNames, dinnerDescriptions);
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
        for (Recipe recipe: filteredRecipes) {
            System.out.println(recipe.getMealType());
        }
        List<Recipe> breakfastRecipes = selectRecipeByMealType(filteredRecipes, "breakfast");
        List<Recipe> lunchRecipes = selectRecipeByMealType(filteredRecipes, "lunch");
        List<Recipe> dinnerRecipes = selectRecipeByMealType(filteredRecipes, "dinner");

        for (int dayIndex = 0; dayIndex < duration; dayIndex++) {
            Recipe breakfast = breakfastRecipes.get(dayIndex % breakfastRecipes.size());
            Recipe lunch = lunchRecipes.get(dayIndex % lunchRecipes.size());
            Recipe dinner = dinnerRecipes.get(dayIndex % dinnerRecipes.size());

            MealPlanDay mealPlanDay = new MealPlanDay(breakfast, lunch, dinner);
            mealPlan.addMealPlanDay(mealPlanDay);
        }

        return mealPlan;
    }


    private List<Recipe> selectRecipeByMealType(List<Recipe> recipes, String mealType) {
        List<Recipe> newRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            String type = recipe.getMealType().get(0);
            if (mealType.equals("breakfast") && (type.equals("snack") || type.equals("breakfast"))) {
                newRecipes.add(recipe);
            } else if (mealType.equals("lunch") && type.contains("lunch")){
                newRecipes.add(recipe);
            } else if (mealType.equals("dinner") && type.contains("dinner")) {
                newRecipes.add(recipe);
            }
        }

        Collections.shuffle(newRecipes); // Shuffle the list
        return newRecipes;
    }

    @Override
    public void back(){
        mealPlanPresenter.back();
    }

}
