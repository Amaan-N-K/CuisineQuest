package interface_adapter.meal_plan_creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import static org.mockito.Mockito.*;

class MealPlanStateTest {

    private MealPlanState mealPlanState;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        mealPlanState = new MealPlanState();
        mockListener = mock(PropertyChangeListener.class);
    }

    @Test
    void addPropertyChangeListener() {
        mealPlanState.addPropertyChangeListener(mockListener);
        mealPlanState.firePropertyChanged("error", null, "Error occurred");
        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void romovePropertyChangeListener() {
        mealPlanState.addPropertyChangeListener(mockListener);
        mealPlanState.removePropertyChangeListener(mockListener);
        mealPlanState.setErrorMessage("Another error");
        verifyNoInteractions(mockListener);
    }
}