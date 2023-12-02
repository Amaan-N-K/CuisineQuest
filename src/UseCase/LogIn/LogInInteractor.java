package UseCase.LogIn;

import Entities.User;

import java.util.Optional;

public class LogInInteractor implements LogInInputBoundry {
    final private LogInDataAccessInterface dataAccessObject;
    final private LogInOutputBoundry presenter;

    public LogInInteractor(LogInDataAccessInterface dataAccessObject, LogInOutputBoundry presenter) {
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(LogInInputData logInInputData) {
        String username = logInInputData.getUsername();
        String password = logInInputData.getPassword();
        if (!dataAccessObject.existByUsername(username)) {
            presenter.prepareFailView("username", "User not found.");
        } else {
            User user = dataAccessObject.getByUsername(username);
            if (!user.getPassword().equals(password)) {
                presenter.prepareFailView("password", "Incorrect password");
            } else {
                dataAccessObject.setActive(user);
                LogInOutputData outputData = new LogInOutputData(user.getUserId(), username);
                presenter.prepareSuccessView(outputData);
            }

        }
    }
}
