package entities;
import java.util.List;

public class Criteria {
    private int calorieGoal;
    private List<String> dietaryRestrictions;
    private List<String> ingredientsAvailable;
    private int prepTime;

    Criteria(int calorieGoal, List<String> dietaryRestrictions, List<String> ingredientsAvailable, int prepTime){
        this.calorieGoal = calorieGoal;
        this.dietaryRestrictions = dietaryRestrictions;
        this.ingredientsAvailable = ingredientsAvailable;
        this.prepTime = prepTime;
    }

    public int getCalorieGoal() { return calorieGoal; }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public List<String> getIngredientsAvailable() {
        return ingredientsAvailable;
    }

    public int getPrepTime() {
        return prepTime;
    }
}
