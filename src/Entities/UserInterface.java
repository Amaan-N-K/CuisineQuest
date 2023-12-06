package Entities;

import java.time.LocalDateTime;
import java.util.List;

public interface UserInterface {
    String getUserId();

    String getUsername();

    String getPassword();


    LocalDateTime getCreationTime();

    List<String> getFavoriteRecipes();
    void addFavoriteRecipe(String recipeDescription);
}
