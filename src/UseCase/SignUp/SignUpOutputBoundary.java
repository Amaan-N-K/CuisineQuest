package UseCase.SignUp;

public interface SignUpOutputBoundary {
    void prepareFailView(String type, String message);

    void prepareSuccessView(SignUpOutputData outputData);
}
