package UseCase.MealPlanCreation;
public interface MealPlanOutputBoundary {
    void presentMealPlan(MealPlanOutputData mealPlanOutputData);
    void prepareFailView(String error);
    void back();
}
