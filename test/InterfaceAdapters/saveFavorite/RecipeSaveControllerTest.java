package InterfaceAdapters.saveFavorite;
import UseCase.save_favorite.RecipeSaveInputBoundary;
import UseCase.save_favorite.RecipeSaveInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class RecipeSaveControllerTest {

    @Mock
    private RecipeSaveInputBoundary recipeSaveInteractor;

    private RecipeSaveController recipeSaveController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeSaveController = new RecipeSaveController(recipeSaveInteractor);
    }

    @Test
    void testExecute() {
        String recipeDescription = "Serve it on ice";

        recipeSaveController.execute(recipeDescription);

        verify(recipeSaveInteractor).execute(refEq(new RecipeSaveInputData(recipeDescription)));
    }
}
