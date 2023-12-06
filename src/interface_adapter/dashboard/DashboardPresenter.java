package interface_adapter.dashboard;

import interface_adapter.meal_plan_creation.MealPlanViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.dashboard.DashboardOutputBoundary;
import use_case.dashboard.DashboardOutputData;

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
