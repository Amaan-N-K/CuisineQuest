package entities;

public class MealPlanDayFactory implements MealPlanDayFactoryInterface {
    public MealPlanDay createMealPlanDay(Recipe breakfastRecipe, Recipe lunchRecipe, Recipe dinnerRecipe) {
        return new MealPlanDay(breakfastRecipe, lunchRecipe, dinnerRecipe);
    }
}
