package Entities;

public class MealPlanDay {
    private Recipe breakfastRecipe;
    private Recipe lunchRecipe;
    private Recipe dinnerRecipe;

    public MealPlanDay(Recipe breakfastRecipe, Recipe lunchRecipe, Recipe dinnerRecipe) {
        this.breakfastRecipe = breakfastRecipe;
        this.lunchRecipe = lunchRecipe;
        this.dinnerRecipe = dinnerRecipe;
    }

    public Recipe getBreakfastRecipe{
        return breakfastRecipe
    }

    public Recipe getLunchRecipe{
        return lunchRecipe
    }

    public Recipe getDinnerRecipe{
        return dinnerRecipe
    }

}
