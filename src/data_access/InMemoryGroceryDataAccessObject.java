package data_access;

import Entities.MealPlan;
import Entities.Recipe;

import java.util.List;
import java.util.ArrayList;

public class InMemoryGroceryDataAccessObject implements GroceryDataAccessObject {
    private final List<Recipe> recipes;

    public InMemoryGroceryDataAccessObject(List<Recipe> recipes) {
        this.recipes = new ArrayList<>(recipes);
    }

    public List<String> getGroceryList(MealPlan mealPlan) {
        List<String> groceryList = new ArrayList<>();
        for (Recipe recipe: mealPlan.getRecipes()) {
            groceryList.addAll(getGroceryListFromRecipes(recipe));
        }
        return groceryList;
    }

    private List<String> getGroceryListFromRecipes(Recipe recipe) {
        return new ArrayList<>(recipe.getIngredients());
    }
}