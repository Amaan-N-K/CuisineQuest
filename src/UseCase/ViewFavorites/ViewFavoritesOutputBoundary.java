package UseCase.ViewFavorites;

public interface ViewFavoritesOutputBoundary {
    void presentNoFavorites();

    void presentFavoriteRecipes(ViewFavoritesOutputData outputData);
}
