package Entities;

import java.util.List;

public interface RecipeFactoryInterface {
    Recipe create(List<String> ingredients,
                  List<String> mealType,
                  List<String> diet,
                  List<String> health,
                  List<String> cuisineType,
                  Nutrition nutrition,
                  String description);
}
