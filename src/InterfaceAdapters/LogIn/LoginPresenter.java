package InterfaceAdapters.LogIn;

import InterfaceAdapters.SignUp.SignUpState;
import InterfaceAdapters.ViewManagerModel;
import UseCase.LogIn.LogInOutputBoundry;
import UseCase.LogIn.LogInOutputData;

import java.util.Objects;

public class LoginPresenter implements LogInOutputBoundry {
    private final LogInViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(LogInViewModel loginViewModel,ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    @Override
    public void prepareFailView(String type, String message) {

            LogInState logInState = loginViewModel.getState();
            if (Objects.equals(type, "username")){
                logInState.setUsernameError(message);
                loginViewModel.firePropertyChanged();} else {
                logInState.setPasswordError(message);
                loginViewModel.firePropertyChanged();}

    }

    @Override
    public void prepareSuccessView(LogInOutputData outputData) {
        // Update the login state
        LogInState loginState = loginViewModel.getState();
        loginState.setUserId(outputData.getUserId());
        loginState.setUsername(outputData.getUsername());
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        // Change the view to Dashboard
        viewManagerModel.setActiveView("dashboard");
        viewManagerModel.firePropertyChanged();

    }
}
