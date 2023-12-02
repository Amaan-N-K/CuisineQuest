package InterfaceAdapters.MealPlanCreation;

import Entities.MealPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class MealPlanViewModelTest {
    private MealPlanViewModel viewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        viewModel = new MealPlanViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetStateFiresPropertyChangeEvent() {
        MealPlanState newState = new MealPlanState();
        viewModel.setState(newState);
        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void updateMealPlanState() {
        MealPlan mockMealPlan = mock(MealPlan.class);
        viewModel.updateMealPlanState(mockMealPlan);

        MealPlanState state = viewModel.getState();
        assertTrue(state.isCreationSuccess());
        assertEquals(mockMealPlan, state.getMealPlan());
        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }
}