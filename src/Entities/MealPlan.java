package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MealPlan {
    private final String identifier;
    private final String startDate;
    private final String endDate;
    private final String diet;
    private final int calorieLimit;
    private List<MealPlanDay> recipes;

    public MealPlan(String startDate, String endDate, String diet, int calorieLimit) {
        this.identifier = startDate + "to" + endDate;
        this.recipes = new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.diet = diet;
        this.calorieLimit = calorieLimit;
    }

    public void addMealPlanDay(MealPlanDay mealPlanDay) {
        recipes.add(mealPlanDay);
    }

    public boolean removeMealPlanDay(MealPlanDay mealPlanDay) {
        return recipes.remove(mealPlanDay);
    }

    public List<MealPlanDay> getRecipes() {
        return Collections.unmodifiableList(recipes);
    }

    public String getIdentifier() { return identifier; }
    public String getStartDate() { return startDate; }

    public String getEndDate() {
        return endDate;
    }
    public String getDiets() {
        return diet;
    }
    public int getCalorieLimit() {
        return calorieLimit;
    }


}