package interface_adapter.save_favorite;

import use_case.save_favorite.RecipeSaveInputBoundary;
import use_case.save_favorite.RecipeSaveInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class RecipeSaveControllerTest {

    @Mock
    private RecipeSaveInputBoundary interactor;

    private RecipeSaveController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RecipeSaveController(interactor);
    }

    @Test
    public void testExecuteWithValidData() {
        String recipeDescription = "Test Recipe";
        controller.execute(recipeDescription);

        ArgumentCaptor<RecipeSaveInputData> argument = ArgumentCaptor.forClass(RecipeSaveInputData.class);
        verify(interactor).execute(argument.capture());

        assertEquals(recipeDescription, argument.getValue().getRecipeDescription());
    }

    @Test
    public void testExecuteWithNullData() {
        // Depending on how you want to handle null values, this test can be adjusted.
        String recipeDescription = null;
        controller.execute(recipeDescription);

        ArgumentCaptor<RecipeSaveInputData> argument = ArgumentCaptor.forClass(RecipeSaveInputData.class);
        verify(interactor).execute(argument.capture());

        assertEquals(recipeDescription, argument.getValue().getRecipeDescription());
    }
}
