package InterfaceAdapters.LogIn;

import UseCase.LogIn.LogInInputBoundary;
import UseCase.LogIn.LogInInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

public class LogInControllerTest {

    @Mock
    private LogInInputBoundary logInInteractor;

    private LogInController logInController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logInController = new LogInController(logInInteractor);
    }

    @Test
    void testExecute() {
        String testUsername = "testUser";
        String testPassword = "testPass";

        logInController.execute(testUsername, testPassword);

        verify(logInInteractor).execute(any(LogInInputData.class));
    }
}
