package UseCase.ViewFavorites;

import Entities.User;

import java.util.List;

public class ViewFavoritesInteractor implements ViewFavoritesInputBoundary {
    private final ViewFavoritesOutputBoundary presenter;
    private final ViewFavoritesDataAccessInterface dataAccess;

    public ViewFavoritesInteractor(ViewFavoritesOutputBoundary presenter, ViewFavoritesDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute() {
        String activeUserId = dataAccess.getActive();
        User user = dataAccess.getByID(activeUserId);
        if (user != null) {
            List<String> favoriteRecipes = user.getFavoriteRecipes();
            if (favoriteRecipes.isEmpty()) {
                // For the case where the favorite recipes list is empty
                presenter.presentNoFavorites();
            } else {
                ViewFavoritesOutputData outputData = new ViewFavoritesOutputData(favoriteRecipes);
                presenter.presentFavoriteRecipes(outputData);
            }
        } else {
            // For the case where the active user is not found
            presenter.presentNoFavorites();
        }


    }
}
