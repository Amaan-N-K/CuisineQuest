package Entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NutritionFactoryTest {

    @Test
    void create_shouldCreateNutritionWithGivenValues() {
        int calorie = 200;
        int carbohydrates = 50;
        int protein = 10;
        int sugar = 5;
        int fiber = 3;

        Nutrition nutrition = NutritionFactory.create(calorie, carbohydrates, protein, sugar, fiber);

        assertNotNull(nutrition, "Nutrition object should not be null");
        assertEquals(calorie, nutrition.getCalorie(), "Calories do not match");
        assertEquals(carbohydrates, nutrition.getCarbohydrates(), "Carbohydrates do not match");
        assertEquals(protein, nutrition.getProtein(), "Protein does not match");
        assertEquals(sugar, nutrition.getSugar(), "Sugar does not match");
        assertEquals(fiber, nutrition.getFiber(), "Fiber does not match");
    }
}