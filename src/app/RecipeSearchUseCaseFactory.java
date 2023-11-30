package app;

import InterfaceAdapters.RecipeSearchController;
import InterfaceAdapters.RecipeSearchPresenter;
import InterfaceAdapters.RecipeSearchViewModel;
import UseCase.RecipeDataAccessInterface;
import UseCase.RecipeSearchInputBoundary;
import UseCase.RecipeSearchInteractor;
import UseCase.RecipeSearchOutputBoundary;
import View.RecipeSearchView;

public class RecipeSearchUseCaseFactory {
    private RecipeSearchUseCaseFactory() {
    }

    private static RecipeSearchController createRecipeSearchController(RecipeSearchViewModel recipeSearchViewModel,
                                                RecipeDataAccessInterface recipeDataAccessObject) {

        // Create the output boundary (presenter)
        RecipeSearchOutputBoundary recipeSearchPresenter = new RecipeSearchPresenter(recipeSearchViewModel);

        // Create the interactor (use case) which will handle the search logic
        RecipeSearchInputBoundary recipeSearchInteractor = new RecipeSearchInteractor(recipeSearchPresenter, recipeDataAccessObject);

        // Create and return the RecipeSearchController with the interactor
        return new RecipeSearchController(recipeSearchInteractor);
    }

    public static RecipeSearchView createRecipeSearchView(
            RecipeSearchViewModel recipeSearchViewModel,
            RecipeDataAccessInterface recipeDataAccessObject) {

        RecipeSearchController recipeSearchController = createRecipeSearchController(recipeSearchViewModel, recipeDataAccessObject);

        return new RecipeSearchView(recipeSearchViewModel, recipeSearchController);
    }
}