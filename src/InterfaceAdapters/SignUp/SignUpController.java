
package InterfaceAdapters.SignUp;

import UseCase.SignUp.SignUpInputBoundry;
import UseCase.SignUp.SignUpInputData;

public class SignUpController {
    final SignUpInputBoundry signUpInteractor;

    public SignUpController(SignUpInputBoundry signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    public void execute(String username, String password, String confirmedpassword){
        SignUpInputData input = new SignUpInputData(username, password, confirmedpassword);
        signUpInteractor.execute(input);

    }
}
