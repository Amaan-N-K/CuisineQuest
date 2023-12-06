package use_case.login;

public interface LogInOutputBoundary {
    void prepareFailView(String type, String message);

    void prepareSuccessView(LogInOutputData outputData);
}
