package Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MealPlanDay {
    private final Recipe breakfastRecipe;
    private final Recipe lunchRecipe;
    private final Recipe dinnerRecipe;

    @JsonCreator
    public MealPlanDay(@JsonProperty("breakfastRecipe") Recipe breakfastRecipe, @JsonProperty("lunchRecipe") Recipe lunchRecipe, @JsonProperty("dinnerRecipe") Recipe dinnerRecipe) {
        this.breakfastRecipe = breakfastRecipe;
        this.lunchRecipe = lunchRecipe;
        this.dinnerRecipe = dinnerRecipe;
    }

    public Recipe getBreakfastRecipe(){
        return breakfastRecipe;
    }

    public Recipe getLunchRecipe(){
        return lunchRecipe;
    }

    public Recipe getDinnerRecipe(){
        return dinnerRecipe;
    }

}
