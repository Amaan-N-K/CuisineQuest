package InterfaceAdapters.grocery_list;

import UseCase.grocery_list.GroceryListInputBoundary;
import UseCase.grocery_list.GroceryListInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

class GroceryListControllerTest {

    @Mock
    private GroceryListInputBoundary mockInteractor;

    private GroceryListController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new GroceryListController(mockInteractor);
    }

    @Test
    void testGenerateGroceryList() {
        controller.generateGroceryList();
        verify(mockInteractor).execute(any(GroceryListInputData.class));
    }

    @Test
    void testBack() {
        controller.back();
        verify(mockInteractor).back();
    }

}

