package InterfaceAdapters.recipesearch;

import UseCase.recipesearch.RecipeSearchDTO;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearchState {
    private List<String> recipeNames = new ArrayList<>();
    private List<String> recipeDescriptions = new ArrayList<>();
    private List<List<String>> recipeIngredients = new ArrayList<>();

    private List<String> recipeIDs = new ArrayList<>();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Private setters that update individual aspects of the state
    private void setRecipeNames(List<String> recipeNames) {
        this.recipeNames = new ArrayList<>(recipeNames);
    }

    private void setRecipeDescriptions(List<String> recipeDescriptions) {
        this.recipeDescriptions = new ArrayList<>(recipeDescriptions);
    }

    private void  setRecipeIDs(List<String> recipeIDs) {
        this.recipeIDs = recipeIDs;
    }

    private void setRecipeIngredients(List<List<String>> recipeIngredients) {
        this.recipeIngredients = new ArrayList<>();
        for (List<String> ingredientList : recipeIngredients) {
            this.recipeIngredients.add(new ArrayList<>(ingredientList));
        }
    }

    // Public setter that updates the whole state
    public void setRecipes(List<RecipeSearchDTO> recipeSearchDTOList) {
        List<String> names = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<List<String>> ingredients = new ArrayList<>();
        List<String> recipeids = new ArrayList<>();

        for (RecipeSearchDTO dto : recipeSearchDTOList) {
            names.add(dto.getName());
            descriptions.add(dto.getDescription());
            ingredients.add(new ArrayList<>(dto.getIngredients()));
            recipeids.add(dto.getRecipeID());
        }

        // Call the private setters to update the state
        setRecipeNames(names);
        setRecipeDescriptions(descriptions);
        setRecipeIngredients(ingredients);

        // Notify observers that the entire recipes list has been updated
        firePropertyChanged("recipes", null, null);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    protected void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    // Getters for the view to use for displaying data
    public List<String> getRecipeNames() {
        return recipeNames;
    }

    public List<String> getRecipeDescriptions() {
        return recipeDescriptions;
    }

    public List<List<String>> getRecipeIngredients() {
        return recipeIngredients;
    }

    public List<String> getRecipeIDs() {return recipeIDs;}
}
