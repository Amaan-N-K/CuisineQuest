package Entities;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    private final String userId = "user123";
    private final String username = "testUser";
    private final String password = "testPass";
    private final LocalDateTime creationTime = LocalDateTime.now();
    @BeforeEach
    void setUp() {
        user = new User(userId, username, password, creationTime);
    }

    @org.junit.jupiter.api.Test
    void userPropertiesAreCorrectlyInitialized() {
        assertEquals(userId, user.getUserId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(creationTime, user.getCreationTime());
        assertTrue(user.getFavoriteRecipes().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void addingFavoriteRecipeWorksCorrectly() {
        String recipe = "Chocolate Cake";
        user.addFavoriteRecipe(recipe);

        List<String> favoriteRecipes = user.getFavoriteRecipes();
        assertFalse(favoriteRecipes.isEmpty());
        assertEquals(1, favoriteRecipes.size());
        assertTrue(favoriteRecipes.contains(recipe));
    }
}