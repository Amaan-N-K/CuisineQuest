package Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private final String userId = "user1";
    private final String username = "testUser";
    private final String password = "Password123";
    private final LocalDateTime creationTime = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        user = new User(userId, username, password, creationTime);
    }

    @Test
    void user_ShouldHaveCorrectAttributes() {
        assertEquals(userId, user.getUserId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(creationTime, user.getCreationTime());
        assertTrue(user.getFavoriteRecipes().isEmpty());
    }

    @Test
    void addFavoriteRecipe_ShouldAddRecipeToList() {
        String recipeDescription = "Delicious Apple Pie";
        user.addFavoriteRecipe(recipeDescription);

        List<String> favoriteRecipes = user.getFavoriteRecipes();
        assertFalse(favoriteRecipes.isEmpty());
        assertTrue(favoriteRecipes.contains(recipeDescription));
    }
}