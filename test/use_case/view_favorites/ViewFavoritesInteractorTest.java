package use_case.view_favorites;

import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ViewFavoritesInteractorTest {

    @Mock
    private ViewFavoritesOutputBoundary mockPresenter;

    @Mock
    private ViewFavoritesDataAccessInterface mockDataAccess;

    private ViewFavoritesInteractor interactor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        interactor = new ViewFavoritesInteractor(mockPresenter, mockDataAccess);
    }

    @Test
    public void testExecute_ActiveUserNotFound() {
        when(mockDataAccess.getActive()).thenReturn("userId");
        when(mockDataAccess.getByID("userId")).thenReturn(null);

        interactor.execute();

        verify(mockPresenter).presentNoFavorites();
    }

    @Test
    public void testExecute_NoFavoriteRecipes() {
        User user = new User("userId", "userName", "password", LocalDateTime.now());
        when(mockDataAccess.getActive()).thenReturn("userId");
        when(mockDataAccess.getByID("userId")).thenReturn(user);

        interactor.execute();

        verify(mockPresenter).presentNoFavorites();
    }

    @Test
    public void testExecute_FavoriteRecipesPresent() {
        User user = new User("userId", "userName", "password", LocalDateTime.now());
        List<String> favorites = Arrays.asList("Recipe 1", "Recipe 2");
        user.addFavoriteRecipe("Des1");

        when(mockDataAccess.getActive()).thenReturn("userId");
        when(mockDataAccess.getByID("userId")).thenReturn(user);

        interactor.execute();

        verify(mockPresenter).presentFavoriteRecipes(any(ViewFavoritesOutputData.class));
    }
}
