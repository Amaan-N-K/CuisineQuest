package interface_adapter.dashboard;

import interface_adapter.ViewManagerModel;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.meal_plan_creation.MealPlanViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.dashboard.DashboardOutputData;
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
    @Mock
    private MealPlanViewModel mealPlanViewModel;

    private DashboardPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new DashboardPresenter(recipeSearchViewModel, viewManagerModel, groceryListViewModel, mealPlanViewModel);
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
