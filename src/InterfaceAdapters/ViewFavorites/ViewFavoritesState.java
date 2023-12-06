package InterfaceAdapters.ViewFavorites;

import java.util.List;

public class ViewFavoritesState {
    private List<String> favoriteRecipes;
    private String message;

    public List<String> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(List<String> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
