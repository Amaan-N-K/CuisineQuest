package interface_adapter.recipe_search;

import use_case.recipe_search.RecipeSearchDTO;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearchState {
    private List<String> recipeNames = new ArrayList<>();
    private List<String> recipeDescriptions = new ArrayList<>();
    private List<List<String>> recipeIngredients = new ArrayList<>();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Private setters that update individual aspects of the state
    private void setRecipeNames(List<String> recipeNames) {
        this.recipeNames = new ArrayList<>(recipeNames);
    }

    private void setRecipeDescriptions(List<String> recipeDescriptions) {
        this.recipeDescriptions = new ArrayList<>(recipeDescriptions);
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

        for (RecipeSearchDTO dto : recipeSearchDTOList) {
            names.add(dto.getName());
            descriptions.add(dto.getDescription());
            ingredients.add(new ArrayList<>(dto.getIngredients()));
        }

        // Call the private setters to update the state
        setRecipeNames(names);
        setRecipeDescriptions(descriptions);
        setRecipeIngredients(ingredients);

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
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

}
