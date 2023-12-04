package InterfaceAdapters.grocery_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import UseCase.grocery_list.GroceryListInputBoundary;
import UseCase.grocery_list.GroceryListInputData;
import InterfaceAdapters.grocery_list.GroceryListController;

public class GroceryListControllerTest {

    private GroceryListInputBoundary inputBoundary;
    private GroceryListController groceryListController;

    @BeforeEach
    void setUp() {
        inputBoundary = mock(GroceryListInputBoundary.class);
        groceryListController = new GroceryListController(inputBoundary);
    }

    @Test
    void testGenerateGroceryListSuccess() {

        GroceryListInputData inputData = new GroceryListInputData(true);

        groceryListController.generateGroceryList();

        verify(inputBoundary, times(1)).execute(inputData);
    }

    @Test
    void testGenerateGroceryListFailure() {

        GroceryListInputData inputData = new GroceryListInputData(false);

        groceryListController.generateGroceryList();

        verify(inputBoundary, times(1)).execute(inputData);
    }
}
