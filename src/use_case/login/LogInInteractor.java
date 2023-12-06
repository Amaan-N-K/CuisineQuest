package use_case.login;

import entities.User;

public class LogInInteractor implements LogInInputBoundary {
    final private LogInDataAccessInterface dataAccessObject;
    final private LogInOutputBoundary presenter;

    public LogInInteractor(LogInDataAccessInterface dataAccessObject, LogInOutputBoundary presenter) {
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
