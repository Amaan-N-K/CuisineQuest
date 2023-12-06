package interface_adapter.save_favorite;

import use_case.save_favorite.RecipeSaveOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeSavePresenterTest {

    @Mock
    private RecipeSaveViewModel recipesaveViewModel;

    private RecipeSavePresenter presenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new RecipeSavePresenter(recipesaveViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        RecipeSaveOutputData outputData = new RecipeSaveOutputData("Save successful");
        RecipeSaveState state = new RecipeSaveState();

        when(recipesaveViewModel.getState()).thenReturn(state);

        presenter.prepareSuccessView(outputData);

        verify(recipesaveViewModel).getState();
        verify(recipesaveViewModel).setState(state);
        verify(recipesaveViewModel).firePropertyChanged();

        assert state.isSaveSuccessful();
        assert "Save successful".equals(state.getMessage());
    }

    @Test
    public void testPrepareFailView() {
        RecipeSaveOutputData outputData = new RecipeSaveOutputData("Save failed");
        RecipeSaveState state = new RecipeSaveState();

        when(recipesaveViewModel.getState()).thenReturn(state);

        presenter.prepareFailView(outputData);

        verify(recipesaveViewModel).getState();
        verify(recipesaveViewModel).setState(state);
        verify(recipesaveViewModel).firePropertyChanged();

        assert !state.isSaveSuccessful();
        assert "Save failed".equals(state.getMessage());
    }
}
