package InterfaceAdapters.saveFavorite;

import InterfaceAdapters.SignUp.SignUpState;
import InterfaceAdapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipeSaveViewModel extends ViewModel {

    private RecipeSaveState state = new RecipeSaveState();

    public RecipeSaveViewModel() {
        super("recipe search");
    }

    public void setState(RecipeSaveState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }
    public RecipeSaveState getState() {
        return state;
    }
}
