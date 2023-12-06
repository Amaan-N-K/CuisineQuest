package use_case.recipe_search;

public interface RecipeSearchInputBoundary {
    void execute(RecipeSearchInputData inputData);
    void back();
}