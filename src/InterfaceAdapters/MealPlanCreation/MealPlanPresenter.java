package InterfaceAdapters.MealPlanCreation;

import InterfaceAdapters.ViewManagerModel;
import UseCase.MealPlanCreation.MealPlanOutputBoundary;
import UseCase.MealPlanCreation.MealPlanOutputData;
public class MealPlanPresenter implements MealPlanOutputBoundary {
    private MealPlanViewModel mealPlanviewModel;
    private ViewManagerModel viewManagerModel;

    public MealPlanPresenter(ViewManagerModel viewManagerModel, MealPlanViewModel mealPlanViewModel){
        this.viewManagerModel = viewManagerModel;
        this.mealPlanviewModel = mealPlanViewModel;
    }

    @Override
    public void presentMealPlan(MealPlanOutputData response){
        MealPlanState state = mealPlanviewModel.getState();
        state.setCreationSuccess(true);
        state.setMealPlan(response.getMealPlan());
        mealPlanviewModel.setState(state);
        mealPlanviewModel.firePropertyChanged();

        viewManagerModel.setActiveView("MealPlanDisplayView");
        viewManagerModel.firePropertyChanged();

    }
    @Override
    public void prepareFailView(String message) {
        MealPlanState state = mealPlanviewModel.getState();
        state.setCreationSuccess(false);
        state.setErrorMessage(message);
        mealPlanviewModel.setState(state);
        mealPlanviewModel.firePropertyChanged();
    }
}
