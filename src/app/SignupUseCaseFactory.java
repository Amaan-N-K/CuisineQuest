package app;

import entities.PasswordValidator;
import entities.UserFactory;
import interface_adapter.login.LogInViewModel;
import interface_adapter.sign_up.SignUpController;
import interface_adapter.sign_up.SignUpPresenter;
import interface_adapter.sign_up.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import use_case.signup.SignUpDataAccessInterface;
import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpInteractor;
import use_case.signup.SignUpOutputBoundary;
import view.SignUpView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignUpView create(
            ViewManagerModel viewManagerModel,
            LogInViewModel loginViewModel,
            SignUpViewModel signupViewModel,
            SignUpDataAccessInterface userDataAccessObject) {

        try {
            SignUpController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignUpView(signupController, signupViewModel, viewManagerModel);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignUpController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignUpViewModel signupViewModel, LogInViewModel loginViewModel, SignUpDataAccessInterface userDataAccessObject) throws IOException {

        SignUpOutputBoundary signupOutputBoundary = new SignUpPresenter(signupViewModel, loginViewModel, viewManagerModel);

        UserFactory userFactory = new UserFactory();
        PasswordValidator passwordValidator = new PasswordValidator();

        SignUpInputBoundary userSignupInteractor = new SignUpInteractor(
                userDataAccessObject, signupOutputBoundary, passwordValidator, userFactory);

        return new SignUpController(userSignupInteractor);
    }
}
