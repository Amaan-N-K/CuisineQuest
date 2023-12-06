package interface_adapter.meal_plan_creation;

import use_case.meal_plan_creation.MealPlanOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MealPlanViewModelTest {

    private MealPlanViewModel viewModel;
    private PropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        viewModel = new MealPlanViewModel();
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    void testUpdateMealPlanState() {
        List<String> breakfastNames = Arrays.asList("Breakfast1", "Breakfast2");
        List<String> breakfastDescriptions = Arrays.asList("Desc1", "Desc2");
        List<String> lunchNames = Arrays.asList("lunch1", "lunch2");
        List<String> lunchDescriptions = Arrays.asList("Desc1", "Desc2");
        List<String> dinnerNames = Arrays.asList("dinner1", "dinner2");
        List<String> dinnerDescriptions = Arrays.asList("Desc1", "Desc2");
        MealPlanOutputData data = new MealPlanOutputData(breakfastNames, breakfastDescriptions, lunchNames, lunchDescriptions, dinnerNames, dinnerDescriptions);
        viewModel.updateMealPlanState(data);

        MealPlanState state = viewModel.getState();
        assertEquals(breakfastNames, state.getBreakfastNames());
        assertEquals(breakfastDescriptions, state.getBreakfastDescriptions());

        ArgumentCaptor<PropertyChangeEvent> propertyEventCaptor = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(listener).propertyChange(propertyEventCaptor.capture());
        assertEquals("Display MealPlan", propertyEventCaptor.getValue().getPropertyName());

    }

    @Test
    void testSetAndGetState() {
        MealPlanState newState = new MealPlanState();
        viewModel.setState(newState);
        assertEquals(newState, viewModel.getState());
    }
}
