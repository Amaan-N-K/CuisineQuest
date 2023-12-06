package interface_adapter.grocery_list;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class GroceryListState {
    private List<String> groceryList = new ArrayList<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GroceryListState() {}

    public List<String> getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(List<String> groceryList) {
        List<String> oldGroceryList = new ArrayList<>(this.groceryList);
        this.groceryList = new ArrayList<>(groceryList);
        support.firePropertyChange("groceryList", oldGroceryList, groceryList);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
