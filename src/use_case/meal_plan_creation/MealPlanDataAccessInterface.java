package use_case.meal_plan_creation;

import entities.MealPlan;

import java.io.IOException;

public interface MealPlanDataAccessInterface {

    void saveMealPlan(String userId, MealPlan mealPlan) throws IOException;

    MealPlan loadMealPlan(String userId) throws IOException;

}
