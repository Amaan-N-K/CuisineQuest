package UseCase.recipesearch;

public interface RecipeSearchOutputBoundary {
    void present(RecipeSearchOutputData outputData);
    void back();
}