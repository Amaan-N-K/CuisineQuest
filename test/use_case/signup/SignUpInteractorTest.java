package use_case.signup;

import entities.PasswordValidator;
import entities.User;
import entities.UserFactory;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class SignUpInteractorTest {

    @Mock
    private SignUpDataAccessInterface mockDataAccessObject;
    @Mock
    private SignUpOutputBoundary mockPresenter;
    @Mock
    private PasswordValidator mockPassValidator;
    @Mock
    private UserFactory mockUserFactory;

    private SignUpInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        interactor = new SignUpInteractor(mockDataAccessObject, mockPresenter, mockPassValidator, mockUserFactory);
    }

    @Test
    void testExecuteWithExistingUsername() {
        String username = "existingUser";
        SignUpInputData inputData = new SignUpInputData(username, "password", "password");

        when(mockDataAccessObject.existByUsername(username)).thenReturn(true);

        interactor.execute(inputData);

        verify(mockPresenter).prepareFailView("username", "User already exists.");
    }

    @Test
    void testExecuteWithInvalidPassword() {
        String username = "newUser";
        String password = "weak";
        SignUpInputData inputData = new SignUpInputData(username, password, password);

        when(mockDataAccessObject.existByUsername(username)).thenReturn(false);
        when(mockPassValidator.Validate(password)).thenReturn("Password too weak");

        interactor.execute(inputData);

        verify(mockPresenter).prepareFailView("password", "Password too weak");
    }

    @Test
    void testExecuteWithNonMatchingPasswords() {
        String username = "newUser";
        SignUpInputData inputData = new SignUpInputData(username, "password", "differentPassword");

        when(mockDataAccessObject.existByUsername(username)).thenReturn(false);
        when(mockPassValidator.Validate("password")).thenReturn("");

        interactor.execute(inputData);

        verify(mockPresenter).prepareFailView("confirmpassword", "Passwords don't match");
    }

    @Test
    void testExecuteWithSuccessfulSignUp() {
        String username = "newUser";
        String password = "strongPassword";
        SignUpInputData inputData = new SignUpInputData(username, password, password);
        User mockUser = mock(User.class);

        when(mockDataAccessObject.existByUsername(username)).thenReturn(false);
        when(mockPassValidator.Validate(password)).thenReturn("");
        when(mockUserFactory.create(anyString(), eq(username), eq(password), any(LocalDateTime.class))).thenReturn(mockUser);

        interactor.execute(inputData);

        verify(mockDataAccessObject).save(mockUser);
        verify(mockDataAccessObject).setActive(mockUser);
        verify(mockPresenter).prepareSuccessView(any(SignUpOutputData.class));
    }

}
