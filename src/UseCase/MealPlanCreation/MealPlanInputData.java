package UseCase.MealPlanCreation;

import Entities.Recipe;
import java.util.List;

public class MealPlanInputData {
    private final String startDate;
    private final String endDate;
    private final List<String> diet;
    private final int calorieLimit;

    public MealPlanInputData(String startDate, String endDate, List<String> diet, int calorieLimit) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.diet = diet;
        this.calorieLimit = calorieLimit;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public static String getDiet() {
        return diet.toString();
    }

    public int getCalorieLimit() {
        return calorieLimit;
    }
}

