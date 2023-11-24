package Entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private List<Recipe> favoriteRecipes;
    public User(String userId){
        this.userId = userId;
        this.favoriteRecipes = new ArrayList<>() ;
    }

    public String getUserId() {
        return userId;
    }

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }
    public void addFavoriteRecipe(Recipe recipe){
        this.favoriteRecipes.add(recipe);
    }
}