package InterfaceAdapters.ViewFavorites;

import InterfaceAdapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewFavoritesViewModel extends ViewModel {
    private ViewFavoritesState state = new ViewFavoritesState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ViewFavoritesViewModel() {
        super("view favorites");
    }

    public void setState(ViewFavoritesState state) {
        this.state = state;
    }
    public ViewFavoritesState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);

    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
