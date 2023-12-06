package InterfaceAdapters.recipesearch;

import Entities.Nutrition;
import Entities.Recipe;
import UseCase.recipesearch.RecipeSearchDTO;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class RecipeSearchViewModelTest {

    private RecipeSearchViewModel viewModel;

    @Mock
    private PropertyChangeListener mockListener;
    private Recipe breakfast;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new RecipeSearchViewModel();
        viewModel.addPropertyChangeListener(mockListener);

        List<String> ingredientsB = new ArrayList<>();
        ingredientsB.add("watermelon");
        ingredientsB.add("lemon juice");
        List<String> mealTypeB = new ArrayList<>();
        mealTypeB.add("breakfast");
        List<String> dietB = new ArrayList<>();
        dietB.add("Low-Fat");
        List<String> healthB = new ArrayList<>();
        healthB.add("Alcohol-Free");
        List<String> cuisineTypeB = new ArrayList<>();
        cuisineTypeB.add("n/a");
        Nutrition nutritionB = new Nutrition(40, 11, 0, 10,0);
        String descriptionB = "Stir it all together and serve over ice.";
        breakfast = new Recipe("id1", "WaterMelon Lemonade recipes", ingredientsB, mealTypeB, dietB, healthB, cuisineTypeB, nutritionB, descriptionB);
    }



    @Test
    void testPresentRecipes() {
        List<RecipeSearchDTO> recipeList = Arrays.asList(new RecipeSearchDTO(breakfast));
        viewModel.presentRecipes(recipeList);

        verify(mockListener).propertyChange(any());
    }

}
