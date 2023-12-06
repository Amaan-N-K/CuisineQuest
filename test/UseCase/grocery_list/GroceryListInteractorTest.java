package UseCase.grocery_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class GroceryListInteractorTest {

    @Mock
    private GroceryListDataAccessInterface mockDataAccessInterface;
    @Mock
    private GroceryListOutputBoundary mockOutputBoundary;

    private GroceryListInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new GroceryListInteractor(mockDataAccessInterface, mockOutputBoundary);
    }

    @Test
    void execute_WithEmptyGroceryList() {
        // Arrange
        when(mockDataAccessInterface.getGrocerylist()).thenReturn(List.of());
        boolean includeOutOfStock = false; // or true, based on your business logic

        // Act
        interactor.execute(new GroceryListInputData(includeOutOfStock));

        // Assert
        verify(mockOutputBoundary).present(any(GroceryListOutputData.class));
    }

    @Test
    void execute_WithNonEmptyGroceryList() {
        // Arrange
        when(mockDataAccessInterface.getGrocerylist()).thenReturn(List.of("Apples", "Bread"));
        boolean includeOutOfStock = false; // or true, based on your business logic

        // Act
        interactor.execute(new GroceryListInputData(includeOutOfStock));

        // Assert
        verify(mockOutputBoundary).present(any(GroceryListOutputData.class));
    }

    @Test
    void back_ShouldCallOutputBoundaryBack() {
        // Act
        interactor.back();

        // Assert
        verify(mockOutputBoundary).back();
    }
}