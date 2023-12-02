package UseCase.grocery_list;

import UseCase.grocery_list.GroceryListOutputBoundary;

import java.util.List;

public class GroceryListInteractor {
    private final GroceryListDataAccessInteractor groceryListDataAccessInteractor;
    private final GroceryListOutputBoundary groceryListOutputBoundary;

    public GroceryListInteractor(GroceryListDataAccessInteractor groceryListDataAccessInteractor, GroceryListOutputBoundary groceryListOutputBoundary) {
        this.groceryListDataAccessInteractor = groceryListDataAccessInteractor;
        this.groceryListOutputBoundary = groceryListOutputBoundary;
    }

    public void generateGroceryList() {
        List<String> groceryItems = groceryListDataAccessInteractor.getGrocerylist();


        GroceryListOutputData groceryListOutputData;
        if (groceryItems.isEmpty()) {
            groceryListOutputData = new GroceryListOutputData(groceryItems, true);
        }
        else {
            groceryListOutputData = new GroceryListOutputData(groceryItems, false);
        }
        groceryListOutputBoundary.present(groceryListOutputData);
    }
}