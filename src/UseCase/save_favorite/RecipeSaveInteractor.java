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
        User user = recipeSaveInputData.getUser();
        Recipe recipe = recipeSaveInputData.getRecipe();
        if (!dataAccess.existsByID(user.getUserId())) {
            recipeSavePresenter.prepareFailView("User doesn't exists!");
        }
        else {
            User existingUser = dataAccess.GetByID(user.getUserId());
            if (existingUser.getFavoriteRecipes().contains(recipe)){
                recipeSavePresenter.prepareFailView("Recipe already saved!");
            }
            else {
                existingUser.addFavoriteRecipe(recipe);
                dataAccess.updateUser(existingUser);
                String success = "The recipe was successfully added";
                RecipeSaveOutputData outputData = new RecipeSaveOutputData(existingUser, recipe, success);
                recipeSavePresenter.prepareSuccessView(outputData);




            }
        }
    }
}
