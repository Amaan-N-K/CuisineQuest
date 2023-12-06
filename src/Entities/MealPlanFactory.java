package Entities;

public class MealPlanFactory implements MealPlanFactoryInterface {
    public MealPlan createMealPlan(String startDate, String endDate, String diet, int calorieLimit) {
        return new MealPlan(startDate, endDate, diet, calorieLimit);
    }
}
