package data_access;

import Entities.User;
import Entities.UserFactory;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class UserDataAccessObjectTest {

    @Mock
    private UserFactory mockUserFactory;

    private UserDataAccessObject userDataAccessObject;
    private Path tempFilePath;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        tempFilePath = Files.createTempFile("test", ".csv");
        userDataAccessObject = new UserDataAccessObject(tempFilePath.toString(), mockUserFactory);
    }

    @Test
    void testSaveUser() throws IOException {
        User mockUser = mock(User.class);
        when(mockUser.getUserId()).thenReturn("123");
        when(mockUser.getUsername()).thenReturn("testuser");
        when(mockUser.getPassword()).thenReturn("password");
        LocalDateTime creationTime = LocalDateTime.now();
        when(mockUser.getCreationTime()).thenReturn(creationTime);
        when(mockUser.getFavoriteRecipes()).thenReturn(Collections.singletonList("recipe1"));

        userDataAccessObject.save(mockUser);

        List<String> lines = Files.readAllLines(tempFilePath);
        assertTrue(lines.contains(String.format("123,testuser,password,%s,recipe1", creationTime.toString())));
    }

    @Test
    void testGetUserByUsername() {
        User testUser = new User("123", "testuser", "password", LocalDateTime.now());
        userDataAccessObject.save(testUser);

        User retrievedUser = userDataAccessObject.getByUsername("testuser");
        assertEquals("123", retrievedUser.getUserId());
    }

    @Test
    void testGetUserById() {
        User testUser = new User("123", "testuser", "password", LocalDateTime.now());
        userDataAccessObject.save(testUser);

        User retrievedUser = userDataAccessObject.getByID("123");
        assertEquals("testuser", retrievedUser.getUsername());
    }
    @Test
    void testExistByUsername() {
        User mockUser = mock(User.class);
        when(mockUser.getUserId()).thenReturn("123");
        when(mockUser.getUsername()).thenReturn("testuser");
        userDataAccessObject.save(mockUser);

        assertTrue(userDataAccessObject.existByUsername("testuser"));
    }

    @Test
    void testExistsById() {
        User mockUser = mock(User.class);
        when(mockUser.getUserId()).thenReturn("123");
        when(mockUser.getUsername()).thenReturn("testuser");
        userDataAccessObject.save(mockUser);

        assertTrue(userDataAccessObject.existsByID("123"));
    }

    @Test
    void testSetActiveAndGetActive() {
        User mockUser = mock(User.class);
        when(mockUser.getUserId()).thenReturn("123");

        userDataAccessObject.setActive(mockUser);
        assertEquals("123", userDataAccessObject.getActive());
    }
}
