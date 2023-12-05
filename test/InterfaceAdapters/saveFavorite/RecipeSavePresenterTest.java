package InterfaceAdapters.saveFavorite;

import UseCase.save_favorite.RecipeSaveOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class RecipeSavePresenterTest {

    @Mock
    private RecipeSaveViewModel recipesaveViewModel;

    private RecipeSavePresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(recipesaveViewModel.getState()).thenReturn(new RecipeSaveState());
        presenter = new RecipeSavePresenter(recipesaveViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        RecipeSaveOutputData outputData = new RecipeSaveOutputData("Success message");

        presenter.prepareSuccessView(outputData);

        verify(recipesaveViewModel).setState(any(RecipeSaveState.class));
        verify(recipesaveViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailView() {
        RecipeSaveOutputData outputData = new RecipeSaveOutputData("Fail message");

        presenter.prepareFailView(outputData);

        verify(recipesaveViewModel).setState(any(RecipeSaveState.class));
        verify(recipesaveViewModel).firePropertyChanged();
    }
}
