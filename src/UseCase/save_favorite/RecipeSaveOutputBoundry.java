package UseCase.save_favorite;

public interface RecipeSaveOutputBoundry {
    void prepareSuccessView(RecipeSaveOutputData outputData);

    void prepareFailView(String error);
}
