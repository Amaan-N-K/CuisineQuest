package InterfaceAdapters.recipesearch;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import InterfaceAdapters.dashboard.DashboardViewModel;
import InterfaceAdapters.ViewManagerModel;
import UseCase.recipesearch.RecipeSearchOutputData;
import java.util.List;
import UseCase.recipesearch.RecipeSearchDTO;

class RecipeSearchPresenterTest {

    @Mock
    private RecipeSearchViewModel mockRecipeSearchViewModel;
    @Mock
    private DashboardViewModel mockDashboardViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private RecipeSearchPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new RecipeSearchPresenter(mockViewManagerModel, mockRecipeSearchViewModel, mockDashboardViewModel);
    }

    @Test
    void testPresent() {
        List<RecipeSearchDTO> mockRecipeList = List.of(/* mock recipe data */);
        RecipeSearchOutputData outputData = new RecipeSearchOutputData(mockRecipeList);

        presenter.present(outputData);

        verify(mockRecipeSearchViewModel).presentRecipes(mockRecipeList);
    }

    @Test
    void testBack() {
        String dashboardViewName = "dashboard";

        presenter.back();

        verify(mockViewManagerModel).setActiveView(dashboardViewName);
        verify(mockViewManagerModel).firePropertyChanged();
    }

}
