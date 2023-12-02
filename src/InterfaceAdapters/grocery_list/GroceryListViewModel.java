package InterfaceAdapters.grocery_list;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class GroceryListViewModel {

    private GroceryListState state = new GroceryListState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GroceryListViewModel() {}

    public void setState(GroceryListState state) {
        this.state = state;
        firePropertyChanged("state");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GroceryListState getState() {
        return state;
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, null);
    }

    public void updateGroceryList(List<String> groceryList) {
        state.setGroceryList(groceryList);
        firePropertyChanged("groceryList");
    }

    public void errorMessage() {
        firePropertyChanged("groceryError");
    }

}
