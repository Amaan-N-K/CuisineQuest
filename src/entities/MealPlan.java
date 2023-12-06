package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MealPlan implements MealPlanInterface {
    private final String identifier;
    private final String startDate;
    private final String endDate;
    private final String diet;
    private final int calorieLimit;
    private List<MealPlanDay> mealPlanDays;

    @JsonCreator
    public MealPlan(@JsonProperty("startDate") String startDate, @JsonProperty("endDate")String endDate, @JsonProperty("diet") String diet, @JsonProperty("calorieLimit") int calorieLimit) {
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
    public String getDiet() {
        return diet;
    }
    public int getCalorieLimit() {
        return calorieLimit;
    }


}
