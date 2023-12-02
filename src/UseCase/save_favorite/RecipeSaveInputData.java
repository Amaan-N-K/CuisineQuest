package UseCase.save_favorite;

import Entities.Recipe;
import Entities.User;

public class RecipeSaveInputData {
    private final String recipeDescription;


    public RecipeSaveInputData(String recipeDescription) {

        this.recipeDescription = recipeDescription;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

}
