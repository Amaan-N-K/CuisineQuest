package InterfaceAdapters.recipesearch;

import UseCase.recipesearch.RecipeSearchDTO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class RecipeSearchViewModel {

    private RecipeSearchState state;
    private PropertyChangeSupport support;

    public final String viewName = "recipe search";

    public RecipeSearchViewModel() {
        this.state = new RecipeSearchState();
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void setState(RecipeSearchState state) {
        RecipeSearchState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, state);
    }

    public void presentRecipes(List<RecipeSearchDTO> recipeSearchDTOList) {
        // Call the state's method to update the recipes
        state.setRecipes(recipeSearchDTOList);
        // Then notify the listeners about this change
        support.firePropertyChange("recipes", null, state);
    }

    // Delegate getters to the state object
    public List<String> getRecipeNames() {
        return state.getRecipeNames();
    }

}