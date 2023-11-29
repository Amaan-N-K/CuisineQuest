package Entities;

import java.util.List;

public class MealPlanFactory {
    public MealPlan createMealPlan(String startDate, String endDate, String diet, int calorieLimit) {
        return new MealPlan(startDate, endDate, diet, calorieLimit);
    }
}
