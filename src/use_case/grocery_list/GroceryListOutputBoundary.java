package use_case.grocery_list;

public interface GroceryListOutputBoundary {
    void present(GroceryListOutputData groceryListOutputData);

    void back();
}