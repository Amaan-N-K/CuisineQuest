package InterfaceAdapters.LogIn;

import UseCase.LogIn.LogInInputBoundry;
import UseCase.LogIn.LogInInputData;

public class LogInController {
    final LogInInputBoundry logInInteractor;

    public LogInController(LogInInputBoundry logInInteractor) {
        this.logInInteractor = logInInteractor;
    }
    public void execute(String username, String password){
        LogInInputData input = new LogInInputData(username, password);
        logInInteractor.execute(input);

    }
}
