package interface_adapter.grocery_list;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class GroceryListState {
    private List<String> groceryList = new ArrayList<>();

    public GroceryListState() {}

    public List<String> getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(List<String> groceryList) {
        this.groceryList = new ArrayList<>(groceryList);
    }

}
