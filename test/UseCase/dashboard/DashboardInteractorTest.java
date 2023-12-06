package UseCase.dashboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class DashboardInteractorTest {

    @Mock
    private DashboardOutputBoundary mockPresenter;

    private DashboardInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        interactor = new DashboardInteractor(mockPresenter);
    }

    @Test
    void testExecute() {
        String buttonName = "testButton";
        DashboardInputData inputData = new DashboardInputData(buttonName);

        interactor.execute(inputData);

        ArgumentCaptor<DashboardOutputData> outputDataCaptor = ArgumentCaptor.forClass(DashboardOutputData.class);
        verify(mockPresenter).updateView(outputDataCaptor.capture());

        DashboardOutputData capturedOutput = outputDataCaptor.getValue();
        assertEquals(buttonName, capturedOutput.getButtonName());
    }

}
