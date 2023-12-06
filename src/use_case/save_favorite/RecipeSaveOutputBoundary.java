package use_case.save_favorite;

public interface RecipeSaveOutputBoundary {
    void prepareSuccessView(RecipeSaveOutputData outputData);

    void prepareFailView(RecipeSaveOutputData outputData);
}
