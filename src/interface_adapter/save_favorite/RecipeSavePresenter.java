package interface_adapter.save_favorite;

import use_case.save_favorite.RecipeSaveOutputBoundary;
import use_case.save_favorite.RecipeSaveOutputData;

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
