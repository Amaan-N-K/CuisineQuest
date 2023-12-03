package Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CriteriaTest {
    private Criteria criteria;
    private int calorieGoal;
    private List<String> dietaryRestrictions;
    private List<String> ingredientsAvailable;
    private int prepTime;

    @BeforeEach
    void setUp() {
        calorieGoal = 2000;
        dietaryRestrictions = Arrays.asList("Gluten-Free", "Vegan");
        ingredientsAvailable = Arrays.asList("Tomato", "Cucumber", "Lettuce");
        prepTime = 30;

        criteria = new Criteria(calorieGoal, dietaryRestrictions, ingredientsAvailable, prepTime);
    }

    @Test
    void getCalorieGoal() {
        assertEquals(calorieGoal, criteria.getCalorieGoal());
    }

    @Test
    void getDietaryRestrictions() {
        assertEquals(dietaryRestrictions, criteria.getDietaryRestrictions());
    }

    @Test
    void getIngredientsAvailable() {
        assertEquals(ingredientsAvailable, criteria.getIngredientsAvailable());
    }

    @Test
    void getPrepTime() {
        assertEquals(prepTime, criteria.getPrepTime());
    }
}