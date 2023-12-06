package interface_adapter.grocery_list;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;
import java.util.List;

class GroceryListViewModelTest {

    private GroceryListViewModel viewModel;

    @Mock
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new GroceryListViewModel();
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetState() {
        GroceryListState newState = new GroceryListState();
        viewModel.setState(newState);

        verify(mockListener).propertyChange(any());
        assertEquals(newState, viewModel.getState(), "The state should be updated.");
    }

    @Test
    void testUpdateGroceryList() {
        List<String> groceryList = List.of("Milk", "Bread");
        viewModel.updateGroceryList(groceryList);

        verify(mockListener).propertyChange(any());
        assertEquals(groceryList, viewModel.getState().getGroceryList(), "The grocery list in the state should be updated.");
    }

    @Test
    void testErrorMessage() {
        viewModel.errorMessage();

        verify(mockListener).propertyChange(any());
    }

}
