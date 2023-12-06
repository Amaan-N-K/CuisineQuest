package entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFactoryTest {

    @Test
    void testCreateUser() {
        UserFactory userFactory = new UserFactory();

        String userId = "123";
        String username = "testUser";
        String password = "testPass";
        LocalDateTime creationTime = LocalDateTime.now();

        User user = userFactory.create(userId, username, password, creationTime);

        assertEquals(userId, user.getUserId(), "User ID should match the input value");
        assertEquals(username, user.getUsername(), "Username should match the input value");
        assertEquals(password, user.getPassword(), "Password should match the input value");
        assertEquals(creationTime, user.getCreationTime(), "Creation time should match the input value");
    }
}