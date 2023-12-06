package interface_adapter.grocery_list;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class GroceryListViewModel {

    public final String viewName = "grocery list";
    private GroceryListState state = new GroceryListState();
    private PropertyChangeSupport support;

    public GroceryListViewModel() {
        this.state = new GroceryListState();
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public GroceryListState getState() {
        return state;
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, null);
    }

    public void updateGroceryList(List<String> groceryList) {
        GroceryListState oldState = this.state;
        GroceryListState newState = new GroceryListState();
        newState.setGroceryList(groceryList);
        support.firePropertyChange("groceryList", oldState, newState);
    }

    public void errorMessage() {
        firePropertyChanged("groceryError");
    }

}
