package UseCase.LogIn;

public interface LogInOutputBoundary {
    void prepareFailView(String type, String message);

    void prepareSuccessView(LogInOutputData outputData);
}
