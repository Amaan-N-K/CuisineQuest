package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_search.RecipeSearchController;
import interface_adapter.recipe_search.RecipeSearchPresenter;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.save_favorite.RecipeSaveController;
import interface_adapter.save_favorite.RecipeSavePresenter;
import interface_adapter.save_favorite.RecipeSaveViewModel;
import use_case.recipe_search.RecipeDataAccessInterface;
import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchInteractor;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.save_favorite.RecipeSaveInputBoundary;
import use_case.save_favorite.RecipeSaveInteractor;
import use_case.save_favorite.RecipeSaveOutputBoundary;
import view.RecipeSearchView;
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
        return new RecipeSearchView(recipeSearchController, recipeSaveController, recipeSaveViewModel);
    }
}