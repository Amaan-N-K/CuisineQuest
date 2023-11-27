package InterfaceAdapters.MealPlanCreation;

import UseCase.MealPlanCreation.MealPlanInputBoundary;
import UseCase.MealPlanCreation.MealPlanInputData;

public class MealPlanController {
    private final MealPlanInputBoundary mealPlanInteractor;
    private final MealPlanPresenter mealPlanPresenter;

    public MealPlanController(MealPlanInputBoundary mealPlanInteractor, MealPlanPresenter mealPlanPresenter) {
        this.mealPlanInteractor = mealPlanInteractor;
        this.mealPlanPresenter = mealPlanPresenter;
    }

    public void createMealPlan(String startDate, String endDate, String diets, int calorieLimit) {
        MealPlanInputData mealPlanInputData = new MealPlanInputData(startDate, endDate, diets, calorieLimit);
        mealPlanInteractor.createMealPlan(mealPlanInputData);
    }
}
