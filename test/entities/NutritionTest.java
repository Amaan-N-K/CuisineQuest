package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NutritionTest {
    private Nutrition nutrition;
    private int calorie;
    private int carbohydrates;
    private int protein;
    private int sugar;
    private int fiber;

    @BeforeEach
    void setUp() {
        calorie = 500;
        carbohydrates = 100;
        protein = 25;
        sugar = 50;
        fiber = 10;

        nutrition = new Nutrition(calorie, carbohydrates, protein, sugar, fiber);
    }

    @Test
    void getCalorie_shouldReturnCorrectCalorie() {
        assertEquals(calorie, nutrition.getCalorie());
    }

    @Test
    void getCarbohydrates_shouldReturnCorrectCarbohydrates() {
        assertEquals(carbohydrates, nutrition.getCarbohydrates());
    }

    @Test
    void getProtein_shouldReturnCorrectProtein() {
        assertEquals(protein, nutrition.getProtein());
    }

    @Test
    void getSugar_shouldReturnCorrectSugar() {
        assertEquals(sugar, nutrition.getSugar());
    }

    @Test
    void getFiber_shouldReturnCorrectFiber() {
        assertEquals(fiber, nutrition.getFiber());
    }
}