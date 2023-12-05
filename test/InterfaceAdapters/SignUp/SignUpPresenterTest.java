package InterfaceAdapters.SignUp;

import InterfaceAdapters.LogIn.LogInState;
import InterfaceAdapters.LogIn.LogInViewModel;
import InterfaceAdapters.ViewManagerModel;
import UseCase.SignUp.SignUpOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SignUpPresenterTest {

    @Mock
    private SignUpViewModel signupViewModel;
    @Mock
    private LogInViewModel loginViewModel;
    @Mock
    private ViewManagerModel viewManagerModel;

    private SignUpPresenter signUpPresenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        signUpPresenter = new SignUpPresenter(signupViewModel, loginViewModel, viewManagerModel);
    }

    @Test
    void testPrepareFailView() {
        String type = "username";
        String message = "Username error message";

        SignUpState mockSignUpState = mock(SignUpState.class);

        when(signupViewModel.getState()).thenReturn(mockSignUpState);

        signUpPresenter.prepareFailView(type, message);

        verify(mockSignUpState).setUsernameError(message);
    }


    @Test
    void testPrepareSuccessView() {

        String userId = "123";
        String username = "testUser";
        LocalDateTime currentTime = LocalDateTime.now();

        SignUpOutputData outputData = new SignUpOutputData(userId, username, currentTime);

        SignUpState mockSignUpState = mock(SignUpState.class);
        LogInState mockLoginState = mock(LogInState.class);

        when(signupViewModel.getState()).thenReturn(mockSignUpState);
        when(loginViewModel.getState()).thenReturn(mockLoginState);

        signUpPresenter.prepareSuccessView(outputData);

        verify(mockLoginState).setUserId(userId);
        verify(mockLoginState).setUsername(username);
        verify(loginViewModel).setState(mockLoginState);
        verify(loginViewModel).firePropertyChanged();

        verify(mockSignUpState).setUserId(userId);
        verify(signupViewModel).setState(mockSignUpState);
        verify(signupViewModel).firePropertyChanged();

        verify(viewManagerModel).setActiveView("dashboard");
        verify(viewManagerModel).firePropertyChanged();
    }
}
