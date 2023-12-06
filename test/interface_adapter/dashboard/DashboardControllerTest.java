package interface_adapter.dashboard;

import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

class DashboardControllerTest {

    @Mock
    private DashboardInputBoundary inputBoundary;

    private DashboardController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new DashboardController(inputBoundary);
    }

    @Test
    void testExecute() {
        String testButtonName = "TestButton";

        controller.execute(testButtonName);

        verify(inputBoundary).execute(argThat(new ArgumentMatcher<DashboardInputData>() {
            @Override
            public boolean matches(DashboardInputData argument) {
                return argument.getButtonName().equals(testButtonName);
            }
        }));
    }
}