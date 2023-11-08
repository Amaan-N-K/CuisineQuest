package Entities;

import java.util.List;

public class RecipeFactory implements RecipeFactoryInterface {


    public Recipe create(List<String> ingredients,
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
    ) {
        Nutrition nutrition = NutritionFactory.create(calorie, carbohydrates, protein, sugar, fiber);

        return new Recipe(ingredients, mealType, diet, health, cuisineType, nutrition, description);
    }

}
