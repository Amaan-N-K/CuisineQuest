package InterfaceAdapters.grocery_list;

import UseCase.grocery_list.GroceryListInputBoundary;
import UseCase.grocery_list.GroceryListInputData;
import entities.MealPlan;

public class GroceryListController {

    private final GroceryListInputBoundary groceryListInteractor;

    public GroceryListController(GroceryListInputBoundary groceryListInteractor) {
        this.groceryListInteractor = groceryListInteractor;
    }

    public void generateGroceryList(MealPlan mealPlan) {
        GroceryListInputData inputData = new GroceryListInputData(mealPlan);

        groceryListInteractor.execute(inputData);
    }
}
