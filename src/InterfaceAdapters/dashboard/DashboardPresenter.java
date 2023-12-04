package InterfaceAdapters.dashboard;

import InterfaceAdapters.MealPlanCreation.MealPlanViewModel;
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

    private final MealPlanViewModel mealPlanViewModel;

    public DashboardPresenter(RecipeSearchViewModel recipeSearchViewModel, ViewManagerModel viewManagerModel,
                              GroceryListViewModel groceryListViewModel, MealPlanViewModel mealPlanViewModel) {
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.groceryListViewModel = groceryListViewModel;
        this.mealPlanViewModel = mealPlanViewModel;
    }

    @Override
    public void updateView(DashboardOutputData outputData) {
        if (outputData.getButtonName().equals("RecipeSearch")) {
            viewManagerModel.setActiveView(recipeSearchViewModel.viewName);
            viewManagerModel.firePropertyChanged();
        } else if (outputData.getButtonName().equals("GroceryList")) {
            viewManagerModel.setActiveView(groceryListViewModel.viewName);
            viewManagerModel.firePropertyChanged();

        } else if (outputData.getButtonName().equals("MealPlan")) {
            viewManagerModel.setActiveView(mealPlanViewModel.viewName);
            viewManagerModel.firePropertyChanged();
        }
    }
}
