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

    public List<String> getGroceryItems(MealPlan mealPlan) {
        List<String> groceryItems = new ArrayList<>();
        for (Recipe recipe: mealPlan.getRecipes()) {
            groceryItems.addAll(getGroceryItemsFromRecipes(recipe));
        }
        return groceryItems;
    }

    private List<String> getGroceryItemsFromRecipes(Recipe recipe) {
        return new ArrayList<>(recipe.getIngredients());
    }
}