package UseCase.save_favorite;
import Entities.Recipe;
import Entities.User;

public class RecipeSaveInteractor implements RecipeSaveInputBoundry{
    private final RecipeSaveOutputBoundry recipeSavePresenter;
    private final UserDataAccessInterface dataAccess;

    public RecipeSaveInteractor(RecipeSaveOutputBoundry outputBoundry, UserDataAccessInterface dataAccess){
        this.recipeSavePresenter = outputBoundry;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(RecipeSaveInputData recipeSaveInputData) {
        String userId = recipeSaveInputData.getUserID();
        String recipeId = recipeSaveInputData.getRecipeID();
        if (!dataAccess.existsByID(userId)) {
            RecipeSaveOutputData fail = new RecipeSaveOutputData("User doesn't exists!");
            recipeSavePresenter.prepareFailView(fail);
        }
        else {
            User existingUser = dataAccess.getByID(userId);
            if (existingUser.getFavoriteRecipes().contains(recipeId)){
                RecipeSaveOutputData fail = new RecipeSaveOutputData("Recipe already saved!");
                recipeSavePresenter.prepareFailView(fail);
            }
            else {
                existingUser.addFavoriteRecipe(recipeId);
                dataAccess.save(existingUser);
                String success = "The recipe was successfully added";
                RecipeSaveOutputData outputData = new RecipeSaveOutputData(success);
                recipeSavePresenter.prepareSuccessView(outputData);




            }
        }
    }
}
