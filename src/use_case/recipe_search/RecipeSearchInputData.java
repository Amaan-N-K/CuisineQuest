package use_case.recipe_search;

import java.util.List;
import java.util.ArrayList;

public class RecipeSearchInputData {
    private final int calorieGoal;
    private final List<String> ingredients;
    private final boolean isHalal;
    private final boolean isGlutenFree;
    private final boolean isVegetarian;
    private final boolean isVegan;
    private final boolean isKosher;

    public RecipeSearchInputData(int calorieGoal, List<String> ingredients, boolean isHalal,
                                 boolean isKosher, boolean isGlutenFree, boolean isVegetarian, boolean isVegan) {
        this.calorieGoal = calorieGoal;
        this.ingredients = new ArrayList<>(ingredients); // Create a defensive copy
        this.isHalal = isHalal;
        this.isKosher = isKosher;
        this.isGlutenFree = isGlutenFree;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
    }

    public int getCalorieGoal() {
        return calorieGoal;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients); // Return a defensive copy
    }

    public boolean isHalal() {
        return isHalal;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isKosher() {
        return isKosher;
    }
}
