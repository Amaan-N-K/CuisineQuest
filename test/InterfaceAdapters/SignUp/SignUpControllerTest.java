package InterfaceAdapters.SignUp;

import UseCase.SignUp.SignUpInputBoundary;
import UseCase.SignUp.SignUpInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;

public class SignUpControllerTest {

    @Mock
    private SignUpInputBoundary signUpInteractor;

    private SignUpController signUpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        signUpController = new SignUpController(signUpInteractor);
    }

    @Test
    void testExecute() {
        String username = "testUser";
        String password = "password123";
        String confirmedPassword = "password123";

        signUpController.execute(username, password, confirmedPassword);

        SignUpInputData expectedInputData = new SignUpInputData(username, password, confirmedPassword);
        verify(signUpInteractor).execute(refEq(expectedInputData));
    }
}
