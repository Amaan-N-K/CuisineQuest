package app;

import Entities.PasswordValidator;
import Entities.UserFactory;
import InterfaceAdapters.LogIn.LogInViewModel;
import InterfaceAdapters.SignUp.SignUpController;
import InterfaceAdapters.SignUp.SignUpPresenter;
import InterfaceAdapters.SignUp.SignUpViewModel;
import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.saveFavorite.RecipeSaveViewModel;
import UseCase.SignUp.SignUpDataAccessInterface;
import UseCase.SignUp.SignUpInputBoundary;
import UseCase.SignUp.SignUpInteractor;
import UseCase.SignUp.SignUpOutputBoundary;
import View.SignUpView;

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
