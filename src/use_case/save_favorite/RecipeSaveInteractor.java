package use_case.save_favorite;
import entities.User;

public class RecipeSaveInteractor implements RecipeSaveInputBoundary {
    private final RecipeSaveOutputBoundary recipeSavePresenter;
    private final UserDataAccessInterface dataAccess;

    public RecipeSaveInteractor(RecipeSaveOutputBoundary outputBoundry, UserDataAccessInterface dataAccess){
        this.recipeSavePresenter = outputBoundry;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(RecipeSaveInputData recipeSaveInputData) {
        String userId = dataAccess.getActive();
        String recipeData = recipeSaveInputData.getRecipeDescription();
        if (!dataAccess.existsByID(userId)) {
            RecipeSaveOutputData fail = new RecipeSaveOutputData("User doesn't exists!");
            recipeSavePresenter.prepareFailView(fail);
        }
        else {
            User existingUser = dataAccess.getByID(userId);
            if (existingUser.getFavoriteRecipes().contains(recipeData)){
                RecipeSaveOutputData fail = new RecipeSaveOutputData("Recipe already saved!");
                recipeSavePresenter.prepareFailView(fail);
            }
            else {
                existingUser.addFavoriteRecipe(recipeData);
                dataAccess.save(existingUser);
                String success = "The recipe was successfully added";
                RecipeSaveOutputData outputData = new RecipeSaveOutputData(success);
                recipeSavePresenter.prepareSuccessView(outputData);




            }
        }
    }
}
