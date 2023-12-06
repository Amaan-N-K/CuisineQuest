package interface_adapter.login;

import use_case.login.LogInInputBoundary;
import use_case.login.LogInInputData;

public class LogInController {
    final LogInInputBoundary logInInteractor;

    public LogInController(LogInInputBoundary logInInteractor) {
        this.logInInteractor = logInInteractor;
    }
    public void execute(String username, String password){
        LogInInputData input = new LogInInputData(username, password);
        logInInteractor.execute(input);

    }
}
