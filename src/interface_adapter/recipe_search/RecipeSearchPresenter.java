package interface_adapter.recipe_search;

import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {
    private final RecipeSearchViewModel recipeSearchViewModel;

    private final DashboardViewModel dashboardViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecipeSearchPresenter(ViewManagerModel viewManagerModel, RecipeSearchViewModel recipeSearchViewModel, DashboardViewModel dashboardViewModel) {

        this.recipeSearchViewModel = recipeSearchViewModel;
        this.dashboardViewModel = dashboardViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void present(RecipeSearchOutputData outputData) {
        recipeSearchViewModel.presentRecipes(outputData.getRecipeSearchDTOList());
    }

    @Override
    public void back() {
        viewManagerModel.setActiveView(dashboardViewModel.viewName);
        viewManagerModel.firePropertyChanged();
    }

}
