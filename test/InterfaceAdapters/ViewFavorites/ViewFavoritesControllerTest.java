package InterfaceAdapters.ViewFavorites;

import UseCase.ViewFavorites.ViewFavoritesInputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ViewFavoritesControllerTest {

    @Mock
    private ViewFavoritesInputBoundary mockInteractor;

    private ViewFavoritesController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ViewFavoritesController(mockInteractor);
    }

    @Test
    public void testExecuteCallsInteractorExecute() {
        controller.execute();

        verify(mockInteractor).execute();
    }
}
