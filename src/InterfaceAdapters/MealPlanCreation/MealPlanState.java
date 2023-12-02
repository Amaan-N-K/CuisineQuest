package InterfaceAdapters.MealPlanCreation;
 import Entities.MealPlan;
public class MealPlanState {
    private MealPlan mealPlan;
    private boolean creationSuccess;
    private String errorMessage;
    private String startDate;
    private String endDate;
    private String diet;
    private int calorieLimit;

    public MealPlanState() {
        this.creationSuccess = false;
        this.errorMessage = "";
    }

    // Copy constructor
    public MealPlanState(MealPlanState copy) {
        this.creationSuccess = copy.creationSuccess;
        this.errorMessage = copy.errorMessage;
        this.startDate = copy.startDate;
        this.endDate = copy.endDate;
        this.diet = copy.diet;
        this.calorieLimit = copy.calorieLimit;
    }

    // Getters and setters

    public MealPlan getMealPlan(){
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public boolean isCreationSuccess() {
        return creationSuccess;
    }

    public void setCreationSuccess(boolean creationSuccess) {
        this.creationSuccess = creationSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public int getCalorieLimit() {
        return calorieLimit;
    }

    public void setCalorieLimit(int calorieLimit) {
        this.calorieLimit = calorieLimit;
    }

    // Override toString for debugging purposes
    @Override
    public String toString() {
        return "MealPlanState{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", diet='" + diet + '\'' + ", calorieLimit='" + calorieLimit + '\'' +
                '}';
    }
}