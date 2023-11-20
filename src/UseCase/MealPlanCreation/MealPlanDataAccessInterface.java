package UseCase.MealPlanCreation;

import Entities.MealPlan;
import Entities.Recipe;
import java.util.List;

public interface MealPlanDataAccessInterface {
    List<Recipe> findRecipes(MealPlanInputData mealPlanInputData);

    void saveMealPlan(MealPlan mealPlan);

    MealPlan loadMealPlan();


}
