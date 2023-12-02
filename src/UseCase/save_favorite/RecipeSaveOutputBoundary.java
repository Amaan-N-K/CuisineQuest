package UseCase.save_favorite;

public interface RecipeSaveOutputBoundary {
    void prepareSuccessView(RecipeSaveOutputData outputData);

    void prepareFailView(RecipeSaveOutputData outputData);
}
