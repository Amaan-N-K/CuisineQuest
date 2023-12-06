package interface_adapter.meal_plan_creation;

import use_case.meal_plan_creation.MealPlanInputBoundary;
import use_case.meal_plan_creation.MealPlanInputData;

public class MealPlanController {
    private final MealPlanInputBoundary mealPlanInteractor;


    public MealPlanController(MealPlanInputBoundary mealPlanInteractor) {
        this.mealPlanInteractor = mealPlanInteractor;
    }

    public void createMealPlan(String startDate, String endDate, String diet, int calorieLimit) {
        MealPlanInputData mealPlanInputData = new MealPlanInputData(startDate, endDate, diet, calorieLimit);
        mealPlanInteractor.createMealPlan(mealPlanInputData);
    }
    public void back(){
        mealPlanInteractor.back();
    }
}
