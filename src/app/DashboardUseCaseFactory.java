package app;

import interface_adapter.meal_plan_creation.MealPlanViewModel;
import interface_adapter.view_favorites.ViewFavoritesController;
import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardPresenter;
//import InterfaceAdapters.dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.dashboard.DashboardInputBoundary;
import use_case.dashboard.DashboardInteractor;
import use_case.dashboard.DashboardOutputBoundary;
import view.DashboardView;

public class DashboardUseCaseFactory {
    private DashboardUseCaseFactory() {
    }

    private static DashboardController createDashboardController(RecipeSearchViewModel recipeSearchViewModel,
                                                                 ViewManagerModel viewManagerModel,
                                                                 GroceryListViewModel groceryListViewModel,
                                                                 MealPlanViewModel mealPlanViewModel) {
        // Create the output boundary (presenter)
        DashboardOutputBoundary dashboardPresenter = new DashboardPresenter(recipeSearchViewModel, viewManagerModel, groceryListViewModel, mealPlanViewModel);

        // Create the interactor (use case) which will handle the dashboard logic
        DashboardInputBoundary dashboardInteractor = new DashboardInteractor(dashboardPresenter);

        // Create and return the DashboardController with the interactor
        return new DashboardController(dashboardInteractor);
    }

    public static DashboardView createDashboardView(RecipeSearchViewModel recipeSearchViewModel,
                                                    ViewManagerModel viewManagerModel,
                                                    GroceryListViewModel groceryListViewModel,
                                                    MealPlanViewModel mealPlanViewModel,
                                                    ViewFavoritesController viewFavoritesController) {
        DashboardController dashboardController = createDashboardController(recipeSearchViewModel, viewManagerModel, groceryListViewModel, mealPlanViewModel);

        return new DashboardView(dashboardController, viewFavoritesController);
    }
}
