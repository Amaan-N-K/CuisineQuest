package InterfaceAdapters.MealPlanCreation;

import UseCase.MealPlanCreation.MealPlanInputBoundary;
import UseCase.MealPlanCreation.MealPlanInputData;

public class MealPlanController {
    private final MealPlanInputBoundary mealPlanInteractor;


    public MealPlanController(MealPlanInputBoundary mealPlanInteractor) {
        this.mealPlanInteractor = mealPlanInteractor;
    }

    public void createMealPlan(String startDate, String endDate, String diet, int calorieLimit) {
        MealPlanInputData mealPlanInputData = new MealPlanInputData(startDate, endDate, diet, calorieLimit);
        mealPlanInteractor.createMealPlan(mealPlanInputData);
    }
}
