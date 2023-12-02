package UseCase.SignUp;

import Entities.PasswordValidator;
import Entities.User;
import Entities.UserFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class SignUpInteractor implements SignUpInputBoundry{

    final private SignUpDataAccessInterface dataAccessObject;
    final private SignUpOutputBoundry presenter;
    final private PasswordValidator passValidator;
    final private UserFactory userFactory;

    public SignUpInteractor(SignUpDataAccessInterface dataAccessObject, SignUpOutputBoundry presenter,
                            PasswordValidator passValidator, UserFactory userFactory) {
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
        this.passValidator = passValidator;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignUpInputData signUpInputData) {
        String username = signUpInputData.getUsername();
        if (dataAccessObject.existByUsername(username)) {
            presenter.prepareFailView("username","User already exists.");
        } else if (!passValidator.Validate(signUpInputData.getPassword()).isEmpty()) {
            presenter.prepareFailView("password", passValidator.Validate(signUpInputData.getPassword()));

        } else if (!signUpInputData.getPassword().equals(signUpInputData.getConfirmedPassword())) {
            presenter.prepareFailView("confirmpassword","Passwords don't match");
        } else {
            String userId = UUID.randomUUID().toString();
            LocalDateTime currentTime = LocalDateTime.now();
            User user = userFactory.create(userId, username, signUpInputData.getPassword(), currentTime);
            dataAccessObject.save(user);
            dataAccessObject.setActive(user);
            SignUpOutputData outputData = new SignUpOutputData(userId, username, currentTime);
            presenter.prepareSuccessView(outputData);

        }
    }
}
