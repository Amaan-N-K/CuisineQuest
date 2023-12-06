package use_case.signup;

public interface SignUpOutputBoundary {
    void prepareFailView(String type, String message);

    void prepareSuccessView(SignUpOutputData outputData);
}
