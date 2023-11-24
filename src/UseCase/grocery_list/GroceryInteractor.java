package UseCase.grocery_list;

import Entities.MealPlan;
import Entities.Recipe;
import data_access.GroceryDataAccessObject;
import UseCase.grocery_list.GroceryOutputBoundary.GroceryListOutputBoundary;

import java.util.List;

public class GroceryListInteractor {
    private final GroceryDataAccessObject groceryDataAccessObject;
    private final GroceryOutputBoundary groceryOutputBoundary;

    public GroceryListInteractor(GroceryDataAccessObject groceryDataAccessObject, GroceryListOutputBoundary groceryListOutputBoundary) {
        this.groceryDataAccessObject = groceryDataAccessObject;
        this.groceryListOutputBoundary = groceryListOutputBoundary;
    }

    public void generateGroceryList(MealPlan mealPlan) {
        List<String> groceryItems = groceryDataAccessObject.getGroceryList(mealPlan);

        groceryOutputBoundary.presentGroceryList(groceryItems);
    }
}