package Entities;

import java.util.List;
import java.util.Collections;

public class Recipe {

    private final String name;
    private final List<String> ingredients;
    private final List<String> mealType;
    private final List<String> diet;
    private final List<String> health;
    private final List<String> cuisineType;
    private final Nutrition nutrition;
    private final String description;

    public Recipe(String name,
                  List<String> ingredients,
                  List<String> mealType,
                  List<String> diet,
                  List<String> health,
                  List<String> cuisineType,
                  Nutrition nutrition,
                  String description) {
        this.name = name;
        this.ingredients = Collections.unmodifiableList(ingredients);
        this.mealType = Collections.unmodifiableList(mealType);
        this.diet = Collections.unmodifiableList(diet);
        this.health = Collections.unmodifiableList(health);
        this.cuisineType = Collections.unmodifiableList(cuisineType);
        this.nutrition = nutrition;
        this.description = description;
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