package UseCase.MealPlanCreation;

import Entities.MealPlan;
import Entities.Recipe;

public class MealPlanOutputData {

    private final MealPlan mealPlan;

    public MealPlanOutputData(MealPlan mealPlan){
        this.mealPlan = mealPlan;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }
}
