package use_case.view_favorites;

public interface ViewFavoritesOutputBoundary {
    void presentNoFavorites();

    void presentFavoriteRecipes(ViewFavoritesOutputData outputData);
}
