package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MealPlan {
    private final String identifier;
    private final String startDate;
    private final String endDate;
    private final String diet;
    private final int calorieLimit;
    private List<MealPlanDay> mealPlanDays;

    public MealPlan(String startDate, String endDate, String diet, int calorieLimit) {
        this.identifier = startDate + " to " + endDate;
        this.mealPlanDays = new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.diet = diet;
        this.calorieLimit = calorieLimit;
    }

    public void addMealPlanDay(MealPlanDay mealPlanDay) {
        mealPlanDays.add(mealPlanDay);
    }

    public boolean removeMealPlanDay(MealPlanDay mealPlanDay) {
        return mealPlanDays.remove(mealPlanDay);
    }

    public List<MealPlanDay> getMealPlanDays() {
        return Collections.unmodifiableList(mealPlanDays);
    }

    public String getIdentifier() { return identifier; }
    public String getStartDate() { return startDate; }

    public String getEndDate() {
        return endDate;
    }
    public String getDiets() {
        return diet;
    }
    public int getCalorieLimit() {
        return calorieLimit;
    }


}
