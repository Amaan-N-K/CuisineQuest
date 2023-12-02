package UseCase.save_favorite;

import Entities.Recipe;
import Entities.User;

public class RecipeSaveInputData {
    private final String recipeId;


    public RecipeSaveInputData(String recipeId) {

        this.recipeId = recipeId;
    }

    public String getRecipeID() {
        return recipeId;
    }

}
