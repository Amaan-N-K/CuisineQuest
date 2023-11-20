package InterfaceAdapters.grocery_list;

import InterfaceAdapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class GroceryListViewModel extends ViewModel {

    public static final String MEALPLAN_LABEL = "Meal Plan";
    public static final String GROCERY_BUTTON_LABEL = "Get Grocery List";

    private GroceryListState state = new GroceryListState();
    public GroceryListViewModel() {super("grocery list");}
    public void setState(GroceryListState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GroceryListState getState() {
        return state;
    }
}