package UseCase.MealPlanCreation;

import Entities.MealPlan;
import Entities.Recipe;

import java.io.IOException;
import java.util.List;

public interface MealPlanDataAccessInterface {

    void saveMealPlan(String userId, MealPlan mealPlan) throws IOException;

    MealPlan loadMealPlan(String userId, String identifier) throws IOException;

}
