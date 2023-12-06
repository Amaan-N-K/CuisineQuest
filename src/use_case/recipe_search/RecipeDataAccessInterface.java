package use_case.recipe_search;

import entities.Recipe;

import java.util.List;

public interface RecipeDataAccessInterface {
    List<Recipe> searchReturnResults(RecipeSearchInputData searchData);
}
