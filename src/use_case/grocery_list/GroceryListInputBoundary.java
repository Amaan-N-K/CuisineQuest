package use_case.grocery_list;

public interface GroceryListInputBoundary {
    void execute(GroceryListInputData groceryListInputData);

    void back();
}