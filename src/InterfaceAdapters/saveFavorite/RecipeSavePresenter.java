package InterfaceAdapters.saveFavorite;

import UseCase.save_favorite.RecipeSaveOutputBoundary;
import UseCase.save_favorite.RecipeSaveOutputData;

public class RecipeSavePresenter  implements RecipeSaveOutputBoundary {
    private final RecipeSaveViewModel recipesaveViewModel;

    public RecipeSavePresenter(RecipeSaveViewModel recipesaveViewModel) {
        this.recipesaveViewModel = recipesaveViewModel;
    }

    @Override
    public void prepareSuccessView(RecipeSaveOutputData outputData) {
        RecipeSaveState state = recipesaveViewModel.getState();
        state.setSaveSuccessful(true);
        state.setMessage(outputData.getMessage());
        recipesaveViewModel.setState(state);
        recipesaveViewModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(RecipeSaveOutputData outputData) {
        RecipeSaveState state = recipesaveViewModel.getState();
        state.setSaveSuccessful(false);
        state.setMessage(outputData.getMessage());
        recipesaveViewModel.setState(state);
        recipesaveViewModel.firePropertyChanged();

    }
}
