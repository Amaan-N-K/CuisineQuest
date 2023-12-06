package use_case.save_favorite;

import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class RecipeSaveInteractorTest {

    @Mock
    private RecipeSaveOutputBoundary mockPresenter;

    @Mock
    private UserDataAccessInterface mockDataAccess;

    private RecipeSaveInteractor interactor;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        interactor = new RecipeSaveInteractor(mockPresenter, mockDataAccess);
    }

    @Test
    public void testExecute_UserDoesNotExist() {
        when(mockDataAccess.getActive()).thenReturn("userId");
        when(mockDataAccess.existsByID("userId")).thenReturn(false);

        interactor.execute(new RecipeSaveInputData("recipe"));

        verify(mockPresenter).prepareFailView(any(RecipeSaveOutputData.class));
    }

    @Test
    public void testExecute_RecipeAlreadySaved() {
        User user = new User("userId", "userName", "password", LocalDateTime.now());
        user.addFavoriteRecipe("recipe");

        when(mockDataAccess.getActive()).thenReturn("userId");
        when(mockDataAccess.existsByID("userId")).thenReturn(true);
        when(mockDataAccess.getByID("userId")).thenReturn(user);

        interactor.execute(new RecipeSaveInputData("recipe"));

        verify(mockPresenter).prepareFailView(any(RecipeSaveOutputData.class));
    }

    @Test
    public void testExecute_SuccessfulRecipeSave() {
        User user = new User("userId", "userName", "password", LocalDateTime.now());

        when(mockDataAccess.getActive()).thenReturn("userId");
        when(mockDataAccess.existsByID("userId")).thenReturn(true);
        when(mockDataAccess.getByID("userId")).thenReturn(user);

        interactor.execute(new RecipeSaveInputData("new recipe"));

        verify(mockDataAccess).save(user);
        verify(mockPresenter).prepareSuccessView(any(RecipeSaveOutputData.class));
    }
}
