package UseCase.recipesearch;

public interface RecipeSearchInputBoundary {
    void execute(RecipeSearchInputData inputData);
    void back();
}