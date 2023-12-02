package InterfaceAdapters.MealPlanCreation;

import InterfaceAdapters.ViewManagerModel;
import UseCase.MealPlanCreation.MealPlanOutputBoundary;
import UseCase.MealPlanCreation.MealPlanOutputData;
public class MealPlanPresenter implements MealPlanOutputBoundary {
    private final MealPlanViewModel mealPlanviewModel;
    private ViewManagerModel viewManagerModel;

    public MealPlanPresenter(ViewManagerModel viewManagerModel, MealPlanViewModel mealPlanViewModel){
        this.viewManagerModel = viewManagerModel;
        this.mealPlanviewModel = mealPlanViewModel;
    }

    @Override
    public void presentMealPlan(MealPlanOutputData outputData){
        mealPlanviewModel.updateMealPlanState(outputData.getMealPlan());
        mealPlanviewModel.firePropertyChanged();

        viewManagerModel.setActiveView("MealPlanDisplayView");
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        MealPlanState state = mealPlanviewModel.getState();
        state.setErrorMessage(error);
        mealPlanviewModel.setState(state);
        mealPlanviewModel.firePropertyChanged();
    }
}
