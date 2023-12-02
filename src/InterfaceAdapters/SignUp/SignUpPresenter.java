
package InterfaceAdapters.SignUp;

import InterfaceAdapters.LogIn.LogInState;
import InterfaceAdapters.LogIn.LogInViewModel;
import InterfaceAdapters.ViewManagerModel;
import UseCase.SignUp.SignUpOutputBoundry;
import UseCase.SignUp.SignUpOutputData;

import java.util.Objects;

public class SignUpPresenter implements SignUpOutputBoundry {
    private final SignUpViewModel signupViewModel;
    private final LogInViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public SignUpPresenter(SignUpViewModel signupViewModel, LogInViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareFailView(String type, String message) {
        SignUpState signupState = signupViewModel.getState();
        if (Objects.equals(type, "username")){
        signupState.setUsernameError(message);
        signupViewModel.firePropertyChanged();} else if (type.equals("password")) {
            signupState.setPasswordError(message);
            signupViewModel.firePropertyChanged();}
        else {
            signupState.setConfirmPasswordError(message);
            signupViewModel.firePropertyChanged();
        }


    }

    @Override
    public void prepareSuccessView(SignUpOutputData outputData) {
        //Update Log in state
        LogInState loginState = loginViewModel.getState();
        loginState.setUserId(outputData.getUserId());
        loginState.setUsername(outputData.getUsername());
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        //Update Sign Up view model
        SignUpState signupState = signupViewModel.getState();
        signupState.setUserId(outputData.getUserId());
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();
        // Change the view to Dashboard
        viewManagerModel.setActiveView("MainDashboardView"); // Assuming "MainDashboardView" is the name of the dashboard view
        viewManagerModel.firePropertyChanged();


    }
}
