package entities;

public class NutritionFactory implements NutritionFactoryInterface {
    public static Nutrition create(int calorie, int carbohydrates, int protein, int sugar, int fiber){
        return new Nutrition(calorie, carbohydrates, protein, sugar, fiber);
    }
}
