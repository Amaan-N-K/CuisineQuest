package InterfaceAdapters.dashboard;

import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;
import UseCase.dashboard.DashboardOutputBoundary;
import UseCase.dashboard.DashboardOutputData;

public class DashboardPresenter implements DashboardOutputBoundary {
    private RecipeSearchViewModel recipeSearchViewModel;
    private ViewManagerModel viewManagerModel;

    public DashboardPresenter(RecipeSearchViewModel recipeSearchViewModel, ViewManagerModel viewManagerModel) {
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void updateView(DashboardOutputData outputData) {
        if (outputData.getButtonName().equals("RecipeSearch")) {
            viewManagerModel.setActiveView(recipeSearchViewModel.viewName);
            viewManagerModel.firePropertyChanged();
        }
    }
}
