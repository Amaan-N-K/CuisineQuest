package InterfaceAdapters.dashboard;

import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.grocery_list.GroceryListViewModel;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;
import UseCase.dashboard.DashboardOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

public class DashboardPresenterTest {

    @Mock
    private RecipeSearchViewModel recipeSearchViewModel;
    @Mock
    private ViewManagerModel viewManagerModel;
    @Mock
    private GroceryListViewModel groceryListViewModel;

    private DashboardPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new DashboardPresenter(recipeSearchViewModel, viewManagerModel, groceryListViewModel);
    }

    @Test
    void testUpdateViewToRecipeSearch() {
        DashboardOutputData outputData = new DashboardOutputData("RecipeSearch");

        presenter.updateView(outputData);

        verify(viewManagerModel).setActiveView(recipeSearchViewModel.viewName);
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testUpdateViewToGroceryList() {
        DashboardOutputData outputData = new DashboardOutputData("GroceryList");

        presenter.updateView(outputData);

        verify(viewManagerModel).setActiveView(groceryListViewModel.viewName);
        verify(viewManagerModel).firePropertyChanged();
    }
}
