package data_access;

import Entities.User;
import Entities.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserDataAccessObjectTest {

    private UserDataAccessObject dao;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() throws IOException {
        userFactory = new UserFactory();
        dao = new UserDataAccessObject("test.csv", userFactory);
    }

    @Test
    void addUserAndSave() {
        User newUser = userFactory.create("123", "testUser", "password", LocalDateTime.now());
        dao.save(newUser);

        User retrievedUser = dao.getByUsername("testUser");
        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUsername());
        assertEquals("password", retrievedUser.getPassword());
    }
}