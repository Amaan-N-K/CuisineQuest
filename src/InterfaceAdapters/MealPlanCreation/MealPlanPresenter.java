package InterfaceAdapters.MealPlanCreation;

import InterfaceAdapters.dashboard.DashboardViewModel;
import InterfaceAdapters.ViewManagerModel;
import UseCase.MealPlanCreation.MealPlanOutputBoundary;
import UseCase.MealPlanCreation.MealPlanOutputData;

public class MealPlanPresenter implements MealPlanOutputBoundary {
    private final MealPlanViewModel mealPlanviewModel;
    private ViewManagerModel viewManagerModel;
    private final DashboardViewModel dashboardViewModel;

    public MealPlanPresenter(ViewManagerModel viewManagerModel, MealPlanViewModel mealPlanViewModel, DashboardViewModel dashboardViewModel){
        this.viewManagerModel = viewManagerModel;
        this.mealPlanviewModel = mealPlanViewModel;
        this.dashboardViewModel = dashboardViewModel;
    }

    @Override
    public void presentMealPlan(MealPlanOutputData outputData){
        mealPlanviewModel.updateMealPlanState(outputData.getMealPlan());
        mealPlanviewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        MealPlanState state = mealPlanviewModel.getState();
        state.setErrorMessage(error);
        mealPlanviewModel.setState(state);
        mealPlanviewModel.firePropertyChanged();
    }

    @Override
    public void back() {
        viewManagerModel.setActiveView(dashboardViewModel.viewName);
        viewManagerModel.firePropertyChanged();
    }
}
