package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeFactoryTest {

    private RecipeFactory recipeFactory;

    @BeforeEach
    void setUp() {
        recipeFactory = new RecipeFactory();
    }

    @Test
    void create_ShouldReturnRecipeWithCorrectAttributes() {
        String recipeId = "recipe1";
        String name = "Test Recipe";
        List<String> ingredients = Arrays.asList("Ingredient1", "Ingredient2");
        List<String> mealType = Arrays.asList("Breakfast", "Dinner");
        List<String> diet = Arrays.asList("Vegan");
        List<String> health = Arrays.asList("Low Sugar");
        List<String> cuisineType = Arrays.asList("Canadian");
        String description = "Test Description";
        int calorie = 300;
        int carbohydrates = 50;
        int protein = 20;
        int sugar = 10;
        int fiber = 5;

        Recipe recipe = recipeFactory.create(recipeId, name, ingredients, mealType, diet, health, cuisineType, description, calorie, carbohydrates, protein, sugar, fiber);

        assertNotNull(recipe);
        assertEquals(recipeId, recipe.getRecipeId());
        assertEquals(name, recipe.getName());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(mealType, recipe.getMealType());
        assertEquals(diet, recipe.getDiet());
        assertEquals(health, recipe.getHealth());
        assertEquals(cuisineType, recipe.getCuisineType());
        assertEquals(description, recipe.getDescription());

        Nutrition nutrition = recipe.getNutrition();
        assertNotNull(nutrition);
        assertEquals(calorie, nutrition.getCalorie());
        assertEquals(carbohydrates, nutrition.getCarbohydrates());
        assertEquals(protein, nutrition.getProtein());
        assertEquals(sugar, nutrition.getSugar());
        assertEquals(fiber, nutrition.getFiber());
    }
}