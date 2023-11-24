package UseCase.save_favorite;

import Entities.Recipe;
import Entities.User;

public class RecipeSaveInputData {
    private final Recipe recipe;
    private final User user;

    public RecipeSaveInputData(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public User getUser() {
        return user;
    }
}
