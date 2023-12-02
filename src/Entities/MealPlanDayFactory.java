package Entities;

public class MealPlanDayFactory {
    public MealPlanDay createMealPlanDay(Recipe breakfastRecipe, Recipe lunchRecipe, Recipe dinnerRecipe) {
        return new MealPlanDay(breakfastRecipe, lunchRecipe, dinnerRecipe);
    }
}
