package InterfaceAdapters.SignUp;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import UseCase.SignUp.SignUpInputBoundary;
import UseCase.SignUp.SignUpInputData;
import org.mockito.ArgumentCaptor;
import static org.junit.Assert.*;

class SignUpControllerTest {

    @Mock
    private SignUpInputBoundary mockSignUpInteractor;

    private SignUpController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new SignUpController(mockSignUpInteractor);
    }

    @Test
    void testExecute() {
        String username = "testUser";
        String password = "testPass";
        String confirmedPassword = "testPass";

        controller.execute(username, password, confirmedPassword);

        ArgumentCaptor<SignUpInputData> inputDataCaptor = ArgumentCaptor.forClass(SignUpInputData.class);
        verify(mockSignUpInteractor).execute(inputDataCaptor.capture());

        SignUpInputData capturedInput = inputDataCaptor.getValue();
        assertEquals(username, capturedInput.getUsername());
        assertEquals(password, capturedInput.getPassword());
        assertEquals(confirmedPassword, capturedInput.getConfirmedPassword());
    }

}
