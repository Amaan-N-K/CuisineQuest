package InterfaceAdapters.recipesearch;

import UseCase.recipesearch.RecipeSearchDTO;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class RecipeSearchViewModel {

    public final String viewName = "recipe search";
    private RecipeSearchState state = new RecipeSearchState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RecipeSearchState getState() {
        return state;
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, null);
    }

    public void presentRecipes(List<RecipeSearchDTO> recipeSearchDTOList) {
        state.setRecipes(recipeSearchDTOList);
        firePropertyChanged("recipes");
    }
}