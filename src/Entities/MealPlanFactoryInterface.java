package Entities;

public interface MealPlanFactoryInterface {
    MealPlan createMealPlan(String startDate, String endDate, String diet, int calorieLimit);
}
