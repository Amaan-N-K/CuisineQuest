package Entities;

import java.util.List;
import java.util.Collections;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipe implements RecipeInterface {

    private final String recipeId; // New parameter
    private final String name;
    private final List<String> ingredients;
    private final List<String> mealType;
    private final List<String> diet;
    private final List<String> health;
    private final List<String> cuisineType;
    private final Nutrition nutrition;
    private final String description;

    public Recipe(@JsonProperty("recipeId") String recipeId,
                  @JsonProperty("name")String name,
                  @JsonProperty("ingredients") List<String> ingredients,
                  @JsonProperty("mealType") List<String> mealType,
                  @JsonProperty("diet") List<String> diet,
                  @JsonProperty("health") List<String> health,
                  @JsonProperty("cuisineType") List<String> cuisineType,
                  @JsonProperty("nutrition") Nutrition nutrition,
                  @JsonProperty("description") String description) {
        this.recipeId = recipeId;
        this.name = name;
        this.ingredients = Collections.unmodifiableList(ingredients);
        this.mealType = Collections.unmodifiableList(mealType);
        this.diet = Collections.unmodifiableList(diet);
        this.health = Collections.unmodifiableList(health);
        this.cuisineType = Collections.unmodifiableList(cuisineType);
        this.nutrition = nutrition;
        this.description = description;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }
    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getMealType() {
        return mealType;
    }

    public List<String> getDiet() {
        return diet;
    }

    public List<String> getHealth() {
        return health;
    }

    public List<String> getCuisineType() {
        return cuisineType;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public String getDescription() {
        return description;
    }
}