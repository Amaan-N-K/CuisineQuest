package data_access;

import Entities.MealPlan;
import Entities.Recipe;

import java.util.List;

public interface GroceryDataAccessObject {
    List<String getGroceryList(MealPlan mealPlan);
}