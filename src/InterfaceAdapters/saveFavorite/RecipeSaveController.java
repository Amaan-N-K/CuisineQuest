package InterfaceAdapters.saveFavorite;

import Entities.Recipe;
import Entities.User;
import UseCase.save_favorite.RecipeSaveInputBoundry;
import UseCase.save_favorite.RecipeSaveInputData;

public class RecipeSaveController {
    final RecipeSaveInputBoundry recipeSaveInteractor;

    public RecipeSaveController(RecipeSaveInputBoundry recipeSaveInteractor){
        this.recipeSaveInteractor = recipeSaveInteractor;
    }

    public void execute(String userId, String recipeId){
        RecipeSaveInputData inputData = new RecipeSaveInputData(userId, recipeId);
        recipeSaveInteractor.execute(inputData);

    }

}