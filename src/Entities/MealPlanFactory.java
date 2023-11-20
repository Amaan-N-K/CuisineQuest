package Entities;

import java.util.List;

public class MealPlanFactory {
    public MealPlan createMealPlan(String startDate, String endDate, String diet, int calorieLimit, List<MealPlanDay> recipes) {
        return new MealPlan(startDate, endDate, diet, calorieLimit);
    }
}
