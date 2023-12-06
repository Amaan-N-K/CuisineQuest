package entities;

public interface MealPlanDayFactoryInterface {
    MealPlanDay createMealPlanDay(Recipe breakfastRecipe, Recipe lunchRecipe, Recipe dinnerRecipe);
}
