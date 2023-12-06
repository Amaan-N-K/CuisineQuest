package UseCase.LogIn;

import Entities.User;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class LogInInteractorTest {

    @Mock
    private LogInDataAccessInterface mockDataAccessObject;
    @Mock
    private LogInOutputBoundary mockPresenter;

    private LogInInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        interactor = new LogInInteractor(mockDataAccessObject, mockPresenter);
    }

    @Test
    void testExecuteWithUserNotFound() {
        String username = "nonexistentUser";
        String password = "password";
        LogInInputData inputData = new LogInInputData(username, password);

        when(mockDataAccessObject.existByUsername(username)).thenReturn(false);

        interactor.execute(inputData);

        verify(mockPresenter).prepareFailView("username", "User not found.");
    }

    @Test
    void testExecuteWithIncorrectPassword() {
        String username = "existingUser";
        String correctPassword = "correctPassword";
        String wrongPassword = "wrongPassword";
        LogInInputData inputData = new LogInInputData(username, correctPassword);
        User mockUser = new User("userId", username, wrongPassword, LocalDateTime.now());

        when(mockDataAccessObject.existByUsername(username)).thenReturn(true);
        when(mockDataAccessObject.getByUsername(username)).thenReturn(mockUser);

        interactor.execute(inputData);

        verify(mockPresenter).prepareFailView("password", "Incorrect password");

    }


    @Test
    void testExecuteWithSuccessfulLogin() {
        String username = "existingUser";
        String password = "correctPassword";
        LogInInputData inputData = new LogInInputData(username, password);

        User realUser = new User("userId", username, password, LocalDateTime.now());

        when(mockDataAccessObject.existByUsername(username)).thenReturn(true);
        when(mockDataAccessObject.getByUsername(username)).thenReturn(realUser);

        interactor.execute(inputData);

        verify(mockDataAccessObject).setActive(realUser);
        verify(mockPresenter).prepareSuccessView(any(LogInOutputData.class));
    }


}
