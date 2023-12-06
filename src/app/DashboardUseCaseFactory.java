package app;

import InterfaceAdapters.MealPlanCreation.MealPlanViewModel;
import InterfaceAdapters.ViewFavorites.ViewFavoritesController;
import InterfaceAdapters.ViewFavorites.ViewFavoritesPresenter;
import InterfaceAdapters.ViewFavorites.ViewFavoritesViewModel;
import InterfaceAdapters.dashboard.DashboardController;
import InterfaceAdapters.dashboard.DashboardPresenter;
//import InterfaceAdapters.dashboard.DashboardViewModel;
import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.grocery_list.GroceryListViewModel;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;
import UseCase.ViewFavorites.ViewFavoritesDataAccessInterface;
import UseCase.ViewFavorites.ViewFavoritesInputBoundary;
import UseCase.ViewFavorites.ViewFavoritesInteractor;
import UseCase.ViewFavorites.ViewFavoritesOutputBoundary;
import UseCase.dashboard.DashboardInputBoundary;
import UseCase.dashboard.DashboardInteractor;
import UseCase.dashboard.DashboardOutputBoundary;
import View.DashboardView;

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
