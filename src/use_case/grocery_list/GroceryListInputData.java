package use_case.grocery_list;

public class GroceryListInputData {

    public final boolean success;
    public GroceryListInputData(boolean success){
        this.success = success;
    }

    public boolean success() {
        return success;
    }
}