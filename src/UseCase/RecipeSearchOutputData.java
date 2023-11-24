package UseCase;


import Entities.Recipe;

import java.util.List;

public class RecipeSearchOutputData {
    private final List<Recipe> searchResults;

    // Constructor that initializes the search results and the success status.
    public RecipeSearchOutputData(List<Recipe> searchResults) {
        this.searchResults = searchResults;
    }

    // Getter for the search results.
    public List<Recipe> getSearchResults() {
        return searchResults;
    }

}
