package UseCase.SignUp;

public interface SignUpOutputBoundry {
    void prepareFailView(String type, String message);

    void prepareSuccessView(SignUpOutputData outputData);
}
