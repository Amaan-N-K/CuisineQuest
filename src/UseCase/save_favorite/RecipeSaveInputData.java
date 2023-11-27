package UseCase.save_favorite;

import Entities.Recipe;
import Entities.User;

public class RecipeSaveInputData {
    private final String recipeId;
    private final String userId;

    public RecipeSaveInputData(String userId, String recipeId) {
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public String getRecipeID() {
        return recipeId;
    }

    public String getUserID() {
        return userId;
    }
}
