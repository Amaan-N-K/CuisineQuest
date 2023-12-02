package app;

import InterfaceAdapters.dashboard.DashboardController;
import InterfaceAdapters.dashboard.DashboardPresenter;
//import InterfaceAdapters.dashboard.DashboardViewModel;
import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.grocery_list.GroceryListViewModel;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;
import UseCase.dashboard.DashboardInputBoundary;
import UseCase.dashboard.DashboardInteractor;
import UseCase.dashboard.DashboardOutputBoundary;
import View.DashboardView;

public class DashboardUseCaseFactory {
    private DashboardUseCaseFactory() {
    }

    private static DashboardController createDashboardController(RecipeSearchViewModel recipeSearchViewModel, ViewManagerModel viewManagerModel, GroceryListViewModel groceryListViewModel) {
        // Create the output boundary (presenter)
        DashboardOutputBoundary dashboardPresenter = new DashboardPresenter(recipeSearchViewModel, viewManagerModel, groceryListViewModel);

        // Create the interactor (use case) which will handle the dashboard logic
        DashboardInputBoundary dashboardInteractor = new DashboardInteractor(dashboardPresenter);

        // Create and return the DashboardController with the interactor
        return new DashboardController(dashboardInteractor);
    }

    public static DashboardView createDashboardView(RecipeSearchViewModel recipeSearchViewModel,
                                                    ViewManagerModel viewManagerModel,
                                                    GroceryListViewModel groceryListViewModel) {
        DashboardController dashboardController = createDashboardController(recipeSearchViewModel, viewManagerModel, groceryListViewModel);

        return new DashboardView(dashboardController);
    }
}
