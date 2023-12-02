package Entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void create() {
        UserFactory factory = new UserFactory();
        String expectedUserId = "user123";
        String expectedUsername = "testUser";
        String expectedPassword = "testPass";
        LocalDateTime expectedCreationTime = LocalDateTime.now();

        User user = factory.create(expectedUserId, expectedUsername, expectedPassword, expectedCreationTime);

        assertNotNull(user, "User should not be null");
        assertEquals(expectedUserId, user.getUserId(), "User ID should match");
        assertEquals(expectedUsername, user.getUsername(), "Username should match");
        assertEquals(expectedPassword, user.getPassword());
    }
}