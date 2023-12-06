package use_case.meal_plan_creation;

import entities.Recipe;

import java.io.IOException;
import java.util.List;


public interface MealPlanAPIDataAccessInterface {

    List<Recipe> findRecipes(String diet, int calorieLimit) throws IOException;
}
