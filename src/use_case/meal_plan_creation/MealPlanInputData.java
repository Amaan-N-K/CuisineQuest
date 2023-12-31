package use_case.meal_plan_creation;

public class MealPlanInputData {
    private final String startDate;
    private final String endDate;
    private final String diet;
    private final int calorieLimit;

    public MealPlanInputData(String startDate, String endDate, String diet, int calorieLimit) {
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

    public String getDiet() { return diet; }

    public int getCalorieLimit() {
        return calorieLimit;
    }
}

