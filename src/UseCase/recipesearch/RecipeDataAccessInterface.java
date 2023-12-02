package UseCase.recipesearch;

import Entities.Recipe;

import java.util.List;

public interface RecipeDataAccessInterface {
    List<Recipe> searchReturnResults(RecipeSearchInputData searchData);
}
