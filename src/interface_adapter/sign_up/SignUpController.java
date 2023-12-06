
package interface_adapter.sign_up;

import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpInputData;

public class SignUpController {
    final SignUpInputBoundary signUpInteractor;

    public SignUpController(SignUpInputBoundary signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    public void execute(String username, String password, String confirmedpassword){
        SignUpInputData input = new SignUpInputData(username, password, confirmedpassword);
        signUpInteractor.execute(input);

    }
}
