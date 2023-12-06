package use_case.save_favorite;

public class RecipeSaveInputData {
    private final String recipeDescription;


    public RecipeSaveInputData(String recipeDescription) {

        this.recipeDescription = recipeDescription;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

}
