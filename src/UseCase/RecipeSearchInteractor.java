package UseCase;

public class RecipeSearchInteractor implements RecipeSearchInputBoundary {
    private final RecipeSearchOutputBoundary outputBoundary;
    private final RecipeDataAccessInterface apiSearch;

    // Constructor with dependency injection for the output boundary
    public RecipeSearchInteractor(RecipeSearchOutputBoundary outputBoundary, RecipeDataAccessInterface apiSearch) {
        this.outputBoundary = outputBoundary;
        this.apiSearch = apiSearch;
    }

    @Override
    public void execute(RecipeSearchInputData inputData) {

        RecipeSearchOutputData outputData = new RecipeSearchOutputData(apiSearch.searchReturnResults(inputData)); // You'll need to define the appropriate constructor
        outputBoundary.present(outputData);
    }
}