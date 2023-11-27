package UseCase.MealPlanCreation;

import Entities.MealPlan;
import Entities.Recipe;

import java.io.IOException;
import java.util.List;

public interface MealPlanDataAccessInterface {

    void saveMealPlan(MealPlan mealPlan) throws IOException;

    MealPlan loadMealPlan(String identifier) throws IOException;

}
