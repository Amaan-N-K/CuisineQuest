package use_case.recipe_search;

public interface RecipeSearchOutputBoundary {
    void present(RecipeSearchOutputData outputData);
    void back();
}