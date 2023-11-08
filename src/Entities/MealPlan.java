package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MealPlan {
    private final String startDate;
    private final String endDate;
    private final String diet;
    private final int calorieLimit;
    private List<Recipe> recipes;

    public MealPlan(String startDate, String endDate, String diet, int calorieLimit) {
        this.recipes = new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.diet = diet;
        this.calorieLimit = calorieLimit;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public boolean removeRecipe(Recipe recipe) {
        return recipes.remove(recipe);
    }

    public List<Recipe> getRecipes() {
        return Collections.unmodifiableList(recipes);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public String getDiet() {
        return diet;
    }
    public int getCalorieLimit() {
        return calorieLimit;
    }
}
