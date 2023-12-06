package interface_adapter.view_favorites;

import interface_adapter.ViewManagerModel;
import use_case.view_favorites.ViewFavoritesOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewFavoritesPresenterTest {

    @Mock
    private ViewFavoritesViewModel mockViewModel;

    @Mock
    private ViewManagerModel mockViewManagerModel;

    private ViewFavoritesPresenter presenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ViewFavoritesPresenter(mockViewModel, mockViewManagerModel);
    }

    @Test
    public void testPresentNoFavorites() {
        when(mockViewModel.getViewName()).thenReturn("favoritesView");

        presenter.presentNoFavorites();

        verify(mockViewModel).setState(any(ViewFavoritesState.class));
        verify(mockViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("favoritesView");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPresentFavoriteRecipes() {
        List<String> favoriteRecipes = new ArrayList<>();
        favoriteRecipes.add("Recipe 1");
        ViewFavoritesOutputData outputData = new ViewFavoritesOutputData(favoriteRecipes);

        when(mockViewModel.getViewName()).thenReturn("favoritesView");

        presenter.presentFavoriteRecipes(outputData);

        verify(mockViewModel).setState(any(ViewFavoritesState.class));
        verify(mockViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("favoritesView");
        verify(mockViewManagerModel).firePropertyChanged();
    }
}
