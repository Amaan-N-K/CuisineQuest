package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    private Recipe recipe;
    private final String recipeId = "1";
    private final String name = "Test Recipe";
    private final List<String> ingredients = Arrays.asList("Ingredient1", "Ingredient2");
    private final List<String> mealType = Arrays.asList("Breakfast", "Lunch");
    private final List<String> diet = Arrays.asList("Vegetarian", "Vegan");
    private final List<String> health = Arrays.asList("Sugar-free", "Low-fat");
    private final List<String> cuisineType = Arrays.asList("Italian", "Canadian");
    private final Nutrition nutrition = new Nutrition(500, 50, 30, 20, 10);
    private final String description = "Test description";

    @BeforeEach
    void setUp() {
        recipe = new Recipe(recipeId, name, ingredients, mealType, diet, health, cuisineType, nutrition, description);
    }

    @Test
    void getRecipeId_shouldReturnCorrectId() {
        assertEquals(recipeId, recipe.getRecipeId());
    }

    @Test
    void getName_shouldReturnCorrectName() {
        assertEquals(name, recipe.getName());
    }

    @Test
    void getIngredients_shouldReturnUnmodifiableListOfIngredients() {
        assertEquals(ingredients, recipe.getIngredients());
        assertThrows(UnsupportedOperationException.class, () -> recipe.getIngredients().add("New Ingredient"));
    }

    @Test
    void getMealType_shouldReturnUnmodifiableListOfMealTypes() {
        assertEquals(mealType, recipe.getMealType());
        assertThrows(UnsupportedOperationException.class, () -> recipe.getMealType().add("Dinner"));
    }

    @Test
    void getDiet_shouldReturnUnmodifiableListOfDiets() {
        assertEquals(diet, recipe.getDiet());
        assertThrows(UnsupportedOperationException.class, () -> recipe.getDiet().add("Keto"));
    }

    @Test
    void getHealth_shouldReturnUnmodifiableListOfHealthLabels() {
        assertEquals(health, recipe.getHealth());
        assertThrows(UnsupportedOperationException.class, () -> recipe.getHealth().add("Gluten-free"));
    }

    @Test
    void getCuisineType_shouldReturnUnmodifiableListOfCuisineTypes() {
        assertEquals(cuisineType, recipe.getCuisineType());
        assertThrows(UnsupportedOperationException.class, () -> recipe.getCuisineType().add("Indian"));
    }

    @Test
    void getNutrition_shouldReturnCorrectNutritionObject() {
        assertEquals(nutrition, recipe.getNutrition());
    }

    @Test
    void getDescription_shouldReturnCorrectDescription() {
        assertEquals(description, recipe.getDescription());
    }
}