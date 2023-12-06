package interface_adapter.meal_plan_creation;

import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.meal_plan_creation.MealPlanOutputBoundary;
import use_case.meal_plan_creation.MealPlanOutputData;

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

        mealPlanviewModel.updateMealPlanState(outputData);
    }

    @Override
    public void prepareFailView(String error) {
        MealPlanState state = mealPlanviewModel.getState();
        state.setErrorMessage(error);
        mealPlanviewModel.setState(state);
        mealPlanviewModel.firePropertyChanged(error, null, error);
    }

    @Override
    public void back() {
        viewManagerModel.setActiveView(dashboardViewModel.viewName);
        viewManagerModel.firePropertyChanged();
    }
}
