package InterfaceAdapters.LogIn;

import InterfaceAdapters.ViewManagerModel;
import UseCase.LogIn.LogInOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LogInPresenterTest {

    @Mock
    private LogInViewModel loginViewModel;
    @Mock
    private ViewManagerModel viewManagerModel;
    @Mock
    private LogInState mockLoginState;

    private LogInPresenter logInPresenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logInPresenter = new LogInPresenter(loginViewModel, viewManagerModel);

        when(loginViewModel.getState()).thenReturn(mockLoginState);
    }

    @Test
    void testPrepareFailView() {
        String type = "username";
        String message = "Invalid username";

        logInPresenter.prepareFailView(type, message);

        verify(mockLoginState).setUsernameError(message);
        verify(loginViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareSuccessView() {
        LogInOutputData outputData = new LogInOutputData("userId123", "username123");

        logInPresenter.prepareSuccessView(outputData);

        verify(mockLoginState).setUserId(outputData.getUserId());
        verify(mockLoginState).setUsername(outputData.getUsername());
        verify(loginViewModel).setState(mockLoginState);
        verify(loginViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView("dashboard");
        verify(viewManagerModel).firePropertyChanged();
    }
}

