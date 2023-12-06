package data_access;

import entities.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class EdamamAPIDataAccessObjectTest {
    private EdamamAPIDataAccessObject apiClient;

    @BeforeEach
    void setUp() {
        apiClient = new EdamamAPIDataAccessObject();
    }

    @Test
    void findRecipes() {
        String diet = "balanced";
        int calorieLimit = 2000;
        List<Recipe> recipes = apiClient.findRecipes(diet, calorieLimit);

        assertNotNull(recipes);
        assertFalse(recipes.isEmpty());
    }
}