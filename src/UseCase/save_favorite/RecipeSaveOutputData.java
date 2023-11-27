package UseCase.save_favorite;

import Entities.Recipe;
import Entities.User;

public class RecipeSaveOutputData {
    private User user;
    private Recipe recipe;
    private String message;
    public RecipeSaveOutputData(User user, Recipe recipe, String message) {
        this.user = user;
        this.recipe = recipe;
        this.message = message;
    }
}
