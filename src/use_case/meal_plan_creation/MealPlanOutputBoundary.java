package use_case.meal_plan_creation;
public interface MealPlanOutputBoundary {
    void presentMealPlan(MealPlanOutputData mealPlanOutputData);
    void prepareFailView(String error);
    void back();
}
