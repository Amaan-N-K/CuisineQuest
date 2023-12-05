package InterfaceAdapters.MealPlanCreation;

import Entities.MealPlan;
import Entities.MealPlanDay;
import Entities.Nutrition;
import Entities.Recipe;
import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.dashboard.DashboardViewModel;
import UseCase.MealPlanCreation.MealPlanOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class MealPlanPresenterTest {

    @Mock
    private MealPlanViewModel mockMealPlanViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private DashboardViewModel mockDashboardViewModel;

    private MealPlanPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new MealPlanPresenter(mockViewManagerModel, mockMealPlanViewModel, mockDashboardViewModel);
    }

    @Test
    void presentMealPlan_CallsUpdateOnViewModel() {
        MealPlanOutputData outputData = mock(MealPlanOutputData.class);

        presenter.presentMealPlan(outputData);

        verify(mockMealPlanViewModel).updateMealPlanState(outputData);
    }

    @Test
    void prepareFailView_UpdatesStateWithError() {
        String errorMessage = "Error message";
        MealPlanState mockState = new MealPlanState();
        when(mockMealPlanViewModel.getState()).thenReturn(mockState);

        presenter.prepareFailView(errorMessage);

        assertEquals(errorMessage, mockState.getErrorMessage());

        verify(mockMealPlanViewModel).setState(mockState);
        verify(mockMealPlanViewModel).firePropertyChanged(errorMessage, null, errorMessage);
    }

    @Test
    void back_SetsActiveViewToDashboard() {
        String dashboardViewName = "dashboard";

        presenter.back();

        verify(mockViewManagerModel).setActiveView(dashboardViewName);
        verify(mockViewManagerModel).firePropertyChanged();
    }
}