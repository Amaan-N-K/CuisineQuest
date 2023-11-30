package InterfaceAdapters.recipesearch;

import UseCase.recipesearch.RecipeSearchInputBoundary;
import UseCase.recipesearch.RecipeSearchInputData;

import java.util.List;

public class RecipeSearchController {
    private final RecipeSearchInputBoundary recipeSearch;

    public RecipeSearchController(RecipeSearchInputBoundary recipeSearch) {
        this.recipeSearch = recipeSearch;
    }

    public void searchRecipes(int calorieGoal, List<String> ingredients,
                              boolean isHalal, boolean isKosher, boolean isGlutenFree,
                              boolean isVegetarian, boolean isVegan) {
        RecipeSearchInputData inputData = new RecipeSearchInputData(
                calorieGoal, ingredients, isHalal, isKosher,
                isGlutenFree, isVegetarian, isVegan
        );

        recipeSearch.execute(inputData);
    }

    public void back(){
        recipeSearch.back();
    }
}