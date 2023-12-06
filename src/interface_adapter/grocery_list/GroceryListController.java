package interface_adapter.grocery_list;

import use_case.grocery_list.GroceryListInputBoundary;
import use_case.grocery_list.GroceryListInputData;


public class GroceryListController {

    private final GroceryListInputBoundary groceryListInteractor;

    public GroceryListController(GroceryListInputBoundary groceryListInteractor) {
        this.groceryListInteractor = groceryListInteractor;
    }

    public void generateGroceryList() {
        GroceryListInputData inputData = new GroceryListInputData(true);
        groceryListInteractor.execute(inputData);
    }

    public void back(){
        groceryListInteractor.back();
    }
}
