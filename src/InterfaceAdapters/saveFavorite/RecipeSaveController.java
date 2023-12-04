package InterfaceAdapters.saveFavorite;

import UseCase.save_favorite.RecipeSaveInputBoundary;
import UseCase.save_favorite.RecipeSaveInputData;

public class RecipeSaveController {
    final RecipeSaveInputBoundary recipeSaveInteractor;

    public RecipeSaveController(RecipeSaveInputBoundary recipeSaveInteractor){
        this.recipeSaveInteractor = recipeSaveInteractor;
    }

    public void execute(String recipeDescription){
        RecipeSaveInputData inputData = new RecipeSaveInputData(recipeDescription);
        recipeSaveInteractor.execute(inputData);

    }

}
