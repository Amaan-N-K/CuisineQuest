package UseCase.SignUp;

public interface SignUpOutputBoundry {
    void prepareFailView(String validate);

    void prepareSuccessView(SignUpOutputData outputData);
}
