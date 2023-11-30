package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userId;
    private final String username;
    private final String password;
    private final LocalDateTime creationTime;
    private final List<String> favoriteRecipes;
    public User(String userId, String username, String password, LocalDateTime creationTime){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.creationTime = creationTime;
        this.favoriteRecipes = new ArrayList<>() ;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public List<String> getFavoriteRecipes() {
        return favoriteRecipes;
    }
    public void addFavoriteRecipe(String recipeId){
        this.favoriteRecipes.add(recipeId);
    }
}