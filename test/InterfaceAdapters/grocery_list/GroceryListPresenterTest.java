package InterfaceAdapters.grocery_list;

import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.dashboard.DashboardViewModel;
import UseCase.grocery_list.GroceryListOutputData;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class GroceryListPresenterTest {

    @Mock
    private GroceryListViewModel mockGroceryListViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private DashboardViewModel mockDashboardViewModel;

    private GroceryListPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new GroceryListPresenter(mockViewManagerModel, mockGroceryListViewModel, mockDashboardViewModel);
    }

    @Test
    void testPresentSuccess() {
        GroceryListOutputData outputData = new GroceryListOutputData(new ArrayList<>(), false);
        presenter.present(outputData);
        verify(mockGroceryListViewModel).updateGroceryList(outputData.getGroceryList());
        verify(mockGroceryListViewModel, never()).errorMessage();
    }

    @Test
    void testPresentFailure() {
        GroceryListOutputData outputData = new GroceryListOutputData(new ArrayList<>(), true);
        presenter.present(outputData);
        verify(mockGroceryListViewModel, never()).updateGroceryList(outputData.getGroceryList());
        verify(mockGroceryListViewModel).errorMessage();
    }

    @Test
    void testBack() {
        String dashboardViewName = "dashboard";

        presenter.back();

        verify(mockViewManagerModel).setActiveView(dashboardViewName);
        verify(mockViewManagerModel).firePropertyChanged();
    }
}
