package InterfaceAdapters.recipesearch;

import UseCase.recipesearch.RecipeSearchInputBoundary;
import UseCase.recipesearch.RecipeSearchInputData;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class RecipeSearchControllerTest {

    @Mock
    private RecipeSearchInputBoundary mockRecipeSearch;

    private RecipeSearchController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new RecipeSearchController(mockRecipeSearch);
    }



    @Test
    void testSearchRecipes() {
        List<String> ingredients = Arrays.asList("Tomato", "Cheese");
        controller.searchRecipes(500, ingredients, true, false, true, false, true);

        ArgumentCaptor<RecipeSearchInputData> inputDataCaptor = ArgumentCaptor.forClass(RecipeSearchInputData.class);
        verify(mockRecipeSearch).execute(inputDataCaptor.capture());

        RecipeSearchInputData capturedInput = inputDataCaptor.getValue();
        assertEquals(500, capturedInput.getCalorieGoal());
        assertEquals(ingredients, capturedInput.getIngredients());
        assertTrue(capturedInput.isHalal());
        assertFalse(capturedInput.isKosher());
        assertTrue(capturedInput.isGlutenFree());
        assertFalse(capturedInput.isVegetarian());
        assertTrue(capturedInput.isVegan());
    }

    @Test
    void testBack() {
        controller.back();
        verify(mockRecipeSearch).back();
    }

}
