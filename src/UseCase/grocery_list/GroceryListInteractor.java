package UseCase.grocery_list;

import java.util.List;

public class GroceryListInteractor implements GroceryListInputBoundary{
    private final GroceryListDataAccessInterface groceryListDataAccessInterface;

    private final GroceryListOutputBoundary groceryListOutputBoundary;

    public GroceryListInteractor(GroceryListDataAccessInterface groceryListDataAccessInterface, GroceryListOutputBoundary groceryListOutputBoundary) {
        this.groceryListDataAccessInterface = groceryListDataAccessInterface;
        this.groceryListOutputBoundary = groceryListOutputBoundary;
    }

    @Override
    public void execute(GroceryListInputData groceryListInputData) {
        List<String> groceryItems = groceryListDataAccessInterface.getGrocerylist();


        GroceryListOutputData groceryListOutputData;
        if (groceryItems.isEmpty()) {
            groceryListOutputData = new GroceryListOutputData(groceryItems, true);
        }
        else {
            groceryListOutputData = new GroceryListOutputData(groceryItems, false);
        }
        groceryListOutputBoundary.present(groceryListOutputData);
    }

    @Override
    public void back() {
        groceryListOutputBoundary.back();
    }
}