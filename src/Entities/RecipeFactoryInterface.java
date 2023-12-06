package Entities;

import java.util.List;

public interface RecipeFactoryInterface {
    Recipe create(String recipeId, String name, List<String> ingredients,
                  List<String> mealType,
                  List<String> diet,
                  List<String> health,
                  List<String> cuisineType,
                  String description,
                  int calorie,
                  int carbohydrates,
                  int protein,
                  int sugar,
                  int fiber
    );
}
