package UseCase.MealPlanCreation;
public interface MealPlanOutputBoundary {
    public void presentMealPlan(MealPlanOutputData mealPlanOutputData);
    public void prepareFailView(String error);
}
