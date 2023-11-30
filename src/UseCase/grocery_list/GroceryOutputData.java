package UseCase.grocery_list;

import java.util.List;

public class GroceryListOutputData {
    private final List<String> groceryList;

    private boolean useCaseFailed;

    public GroceryListOutputData(List<String> groceryList, boolean useCaseFailed) {
        this.groceryList = groceryList;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getGroceryList() {
        return groceryList;
    }
}