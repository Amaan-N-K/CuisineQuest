package UseCase.MealPlanCreation;

import Entities.Recipe;

import java.io.IOException;
import java.util.List;


public interface MealPlanAPIDataAccessInterface {

    List<Recipe> findRecipes(String diet, int calorieLimit) throws IOException;
}
