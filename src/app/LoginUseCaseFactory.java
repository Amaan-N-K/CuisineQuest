package app;

import Entities.UserFactory;
import InterfaceAdapters.LogIn.LogInController;
import InterfaceAdapters.LogIn.LogInPresenter;
import InterfaceAdapters.LogIn.LogInViewModel;
import InterfaceAdapters.ViewManagerModel;
import UseCase.LogIn.LogInDataAccessInterface;
import UseCase.LogIn.LogInInputBoundary;
import UseCase.LogIn.LogInInteractor;
import UseCase.LogIn.LogInOutputBoundary;
import View.LogInView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LogInView create(
            ViewManagerModel viewManagerModel,
            LogInViewModel loginViewModel,
            LogInDataAccessInterface userDataAccessObject) {

        try {
            LogInController loginController = createLoginUseCase(viewManagerModel, loginViewModel, userDataAccessObject);
            return new LogInView(loginViewModel, loginController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LogInController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LogInViewModel loginViewModel,
            LogInDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LogInOutputBoundary loginOutputBoundary = new LogInPresenter(loginViewModel, viewManagerModel);

        UserFactory userFactory = new UserFactory();

        LogInInputBoundary loginInteractor = new LogInInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LogInController(loginInteractor);
    }
}
