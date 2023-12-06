package Entities;

import java.util.List;

public interface RecipeInterface {
    String getRecipeId();

    String getName();
    List<String> getIngredients();

    List<String> getMealType();

    List<String> getDiet();

    List<String> getHealth();

    List<String> getCuisineType();

    Nutrition getNutrition();

    String getDescription();
}
