package UseCase.LogIn;

public interface LogInOutputBoundry {
    void prepareFailView(String type, String message);

    void prepareSuccessView(LogInOutputData outputData);
}
