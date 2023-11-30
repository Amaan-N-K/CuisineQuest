package InterfaceAdapters;

import UseCase.RecipeSearchDTO;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class RecipeSearchViewModel {

    private RecipeSearchState state = new RecipeSearchState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // There's no need to expose the entire state setter if we only update it from within the ViewModel.
    // Removed the setState method.

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RecipeSearchState getState() {
        return state;
    }

    private void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, null);
    }

    public void presentRecipes(List<RecipeSearchDTO> recipeSearchDTOList) {
        state.setRecipes(recipeSearchDTOList);
        firePropertyChanged("recipes");
    }
}