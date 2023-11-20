package UseCase;

import java.util.List;
import java.util.ArrayList;

public class SearchInputData {
    private final int calorieMin;
    private final int calorieMax;
    private final List<String> ingredients;
    private final boolean isHalal;
    private final boolean isGlutenFree;
    private final boolean isVegetarian;
    private final boolean isVegan;
    private final boolean isKosher;

    public SearchInputData(int calorieMin, int calorieMax, List<String> ingredients, boolean isHalal,
                           boolean isKosher, boolean isGlutenFree, boolean isVegetarian, boolean isVegan) {
        this.calorieMin = calorieMin;
        this.calorieMax = calorieMax;
        this.ingredients = new ArrayList<>(ingredients); // Create a defensive copy
        this.isHalal = isHalal;
        this.isKosher = isKosher;
        this.isGlutenFree = isGlutenFree;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
    }

    public int getCalorieMin() {
        return calorieMin;
    }

    public int getCalorieMax() {
        return calorieMax;
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
