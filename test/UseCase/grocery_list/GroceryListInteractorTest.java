
package UseCase.grocery_list;

import UseCase.grocery_list.GroceryListDataAccessInteractor;
import UseCase.grocery_list.GroceryListInteractor;
import UseCase.grocery_list.GroceryListOutputBoundary;
import UseCase.grocery_list.GroceryListOutputData;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GroceryListInteractorTest {

    private TestGroceryListDataAccessInteractor dataAccessInteractor;
    private GroceryListOutputBoundaryMock outputBoundaryMock;
    private GroceryListInteractor groceryListInteractor;

    @Before
    public void setUp() {
        // Create an instance of the test data access interactor
        dataAccessInteractor = new TestGroceryListDataAccessInteractor();

        // Initialize the interactor with the test data access interactor
        outputBoundaryMock = new GroceryListOutputBoundaryMock();
        groceryListInteractor = new GroceryListInteractor(dataAccessInteractor, outputBoundaryMock);
    }

    @Test
    public void testGenerateGroceryListWithItems() {
        // Set up the test data
        List<String> mockedGroceryList = Arrays.asList("Item1", "Item2", "Item3");
        dataAccessInteractor.setMockedGroceryList(mockedGroceryList);

        // Act
        groceryListInteractor.generateGroceryList();

        // Verify the output boundary is called with the expected data
        assertEquals(mockedGroceryList, outputBoundaryMock.getOutputData().getGroceryList());
        assertFalse(outputBoundaryMock.getOutputData().getUsecaseFailed());
    }

    @Test
    public void testGenerateGroceryListWithEmptyItems() {
        // Set up the test data
        dataAccessInteractor.setMockedGroceryList(Collections.emptyList());

        // Act
        groceryListInteractor.generateGroceryList();

        // Verify the output boundary is called with the expected data
        assertEquals(Collections.emptyList(), outputBoundaryMock.getOutputData().getGroceryList());
        assertTrue(outputBoundaryMock.getOutputData().getUsecaseFailed());
    }

    // A simple implementation of GroceryListDataAccessInteractor for testing
    private static class TestGroceryListDataAccessInteractor implements GroceryListDataAccessInteractor {
        private List<String> mockedGroceryList;

        void setMockedGroceryList(List<String> mockedGroceryList) {
            this.mockedGroceryList = mockedGroceryList;
        }

        @Override
        public List<String> getGrocerylist() {
            return mockedGroceryList;
        }
    }

    // A simple implementation of GroceryListOutputBoundary for testing
    private static class GroceryListOutputBoundaryMock implements GroceryListOutputBoundary {
        private GroceryListOutputData outputData;

        @Override
        public void present(GroceryListOutputData outputData) {
            this.outputData = outputData;
        }

        public GroceryListOutputData getOutputData() {
            return outputData;
        }
    }
}
