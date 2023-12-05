package UseCase.grocery_list;

public interface GroceryListInputBoundary {
    void execute(GroceryListInputData groceryListInputData);

    void back();
}