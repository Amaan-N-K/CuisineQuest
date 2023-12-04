package InterfaceAdapters.dashboard;

import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.grocery_list.GroceryListViewModel;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;
import InterfaceAdapters.grocery_list.GroceryListViewModel;
import UseCase.dashboard.DashboardOutputBoundary;
import UseCase.dashboard.DashboardOutputData;

public class DashboardPresenter implements DashboardOutputBoundary {
    private final RecipeSearchViewModel recipeSearchViewModel;
    private final ViewManagerModel viewManagerModel;

    private final GroceryListViewModel groceryListViewModel;

    public DashboardPresenter(RecipeSearchViewModel recipeSearchViewModel, ViewManagerModel viewManagerModel,
                              GroceryListViewModel groceryListViewModel) {
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.groceryListViewModel = groceryListViewModel;
    }

    @Override
    public void updateView(DashboardOutputData outputData) {
        if (outputData.getButtonName().equals("RecipeSearch")) {
            viewManagerModel.setActiveView(recipeSearchViewModel.viewName);
            viewManagerModel.firePropertyChanged();
        } else if (outputData.getButtonName().equals("GroceryList")) {
            viewManagerModel.setActiveView(groceryListViewModel.viewName);
            viewManagerModel.firePropertyChanged();

        }
    }
}
