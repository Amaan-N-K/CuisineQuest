package Entities;

import java.util.List;

public interface MealPlanInterface {
    void addMealPlanDay(MealPlanDay mealPlanDay);

    boolean removeMealPlanDay(MealPlanDay mealPlanDay);

    List<MealPlanDay> getMealPlanDays();

    String getIdentifier();
    String getStartDate();

    String getEndDate();
    String getDiet();
    int getCalorieLimit();
}
