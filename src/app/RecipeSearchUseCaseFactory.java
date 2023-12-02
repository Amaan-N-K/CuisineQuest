package app;

import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.recipesearch.RecipeSearchController;
import InterfaceAdapters.recipesearch.RecipeSearchPresenter;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;
import InterfaceAdapters.dashboard.DashboardViewModel;
import UseCase.recipesearch.RecipeDataAccessInterface;
import UseCase.recipesearch.RecipeSearchInputBoundary;
import UseCase.recipesearch.RecipeSearchInteractor;
import UseCase.recipesearch.RecipeSearchOutputBoundary;
import View.RecipeSearchView;

public class RecipeSearchUseCaseFactory {
    private RecipeSearchUseCaseFactory() {
    }

    private static RecipeSearchController createRecipeSearchController(RecipeSearchViewModel recipeSearchViewModel, DashboardViewModel dashboardViewModel,
                                                                       RecipeDataAccessInterface recipeDataAccessObject, ViewManagerModel viewManagerModel) {

        // Create the output boundary (presenter)
        RecipeSearchOutputBoundary recipeSearchPresenter = new RecipeSearchPresenter(viewManagerModel, recipeSearchViewModel, dashboardViewModel);

        // Create the interactor (use case) which will handle the search logic
        RecipeSearchInputBoundary recipeSearchInteractor = new RecipeSearchInteractor(recipeSearchPresenter, recipeDataAccessObject);

        // Create and return the RecipeSearchController with the interactor
        return new RecipeSearchController(recipeSearchInteractor);
    }

    public static RecipeSearchView createRecipeSearchView(
            RecipeSearchViewModel recipeSearchViewModel,
            DashboardViewModel dashboardViewModel,
            RecipeDataAccessInterface recipeDataAccessObject, ViewManagerModel viewManagerModel) {

        RecipeSearchController recipeSearchController = createRecipeSearchController(recipeSearchViewModel, dashboardViewModel, recipeDataAccessObject, viewManagerModel);

        return new RecipeSearchView(recipeSearchViewModel, recipeSearchController, null, null);
    }
}