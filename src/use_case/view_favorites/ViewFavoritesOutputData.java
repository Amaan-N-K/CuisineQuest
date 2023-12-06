package use_case.view_favorites;

import java.util.List;

public class ViewFavoritesOutputData {
    private final List<String> favoriteRecipes;

    public ViewFavoritesOutputData(List<String> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    public List<String> getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
