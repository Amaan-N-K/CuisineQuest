package app;

import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.recipesearch.RecipeSearchController;
import InterfaceAdapters.recipesearch.RecipeSearchPresenter;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;
import InterfaceAdapters.dashboard.DashboardViewModel;
import InterfaceAdapters.saveFavorite.RecipeSaveController;
import InterfaceAdapters.saveFavorite.RecipeSavePresenter;
import InterfaceAdapters.saveFavorite.RecipeSaveViewModel;
import UseCase.recipesearch.RecipeDataAccessInterface;
import UseCase.recipesearch.RecipeSearchInputBoundary;
import UseCase.recipesearch.RecipeSearchInteractor;
import UseCase.recipesearch.RecipeSearchOutputBoundary;
import UseCase.save_favorite.RecipeSaveInputBoundary;
import UseCase.save_favorite.RecipeSaveInteractor;
import UseCase.save_favorite.RecipeSaveOutputBoundary;
import View.RecipeSearchView;
import data_access.UserDataAccessObject;

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

    private static RecipeSaveController createRecipeSaveController(
            RecipeSaveViewModel recipeSaveViewModel,
            UserDataAccessObject userDataAccessObject) {


        RecipeSaveOutputBoundary recipeSavePresenter = new RecipeSavePresenter(recipeSaveViewModel);

        RecipeSaveInputBoundary recipeSaveInteractor = new RecipeSaveInteractor(recipeSavePresenter, userDataAccessObject);

        return new RecipeSaveController(recipeSaveInteractor);
    }


    public static RecipeSearchView createRecipeSearchView(
            RecipeSearchViewModel recipeSearchViewModel,
            DashboardViewModel dashboardViewModel,
            RecipeDataAccessInterface recipeDataAccessObject, ViewManagerModel viewManagerModel,
            RecipeSaveViewModel recipeSaveViewModel, UserDataAccessObject userDataAccessObject) {

        RecipeSearchController recipeSearchController = createRecipeSearchController(recipeSearchViewModel, dashboardViewModel, recipeDataAccessObject, viewManagerModel);
        RecipeSaveController recipeSaveController = createRecipeSaveController(recipeSaveViewModel, userDataAccessObject);
        return new RecipeSearchView(recipeSearchViewModel, recipeSearchController, recipeSaveController, recipeSaveViewModel);
    }
}