package View;

import InterfaceAdapters.MealPlanCreation.MealPlanController;
import InterfaceAdapters.MealPlanCreation.MealPlanState;
import InterfaceAdapters.MealPlanCreation.MealPlanViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MealPlanSearchViewTest {

    @Mock
    private MealPlanController mockController;
    @Mock
    private MealPlanViewModel mockViewModel;

    private MealPlanSearchView mealPlanSearchView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mealPlanSearchView = new MealPlanSearchView(mockController, mockViewModel);
    }

    @Test
    public void testGenerateMealPlanButtonAction() {
        JButton generateButton = findButtonByName(mealPlanSearchView, "Generate Meal Plan");
        assertNotNull("Generate Meal Plan button should be present", generateButton);
        generateButton.doClick();
        verify(mockController, times(1)).createMealPlan(anyString(), anyString(), anyString(), anyInt());
    }

    @Test
    public void testBackButtonAction() {
        JButton backButton = findButtonByName(mealPlanSearchView, "Back");
        assertNotNull("Back button should be present", backButton);
        backButton.doClick();
        verify(mockController, times(1)).back();
    }

    @Test
    public void testPropertyChangeUpdatesView() {
        MealPlanState mockState = mock(MealPlanState.class);
        when(mockState.getBreakfastNames()).thenReturn(List.of("Breakfast1"));
        when(mockState.getBreakfastDescriptions()).thenReturn(List.of("Desc1"));
        when(mockState.getLunchNames()).thenReturn(List.of("Lunch1"));
        when(mockState.getLunchDescriptions()).thenReturn(List.of("Desc2"));
        when(mockState.getDinnerNames()).thenReturn(List.of("Dinner1"));
        when(mockState.getDinnerDescriptions()).thenReturn(List.of("Desc3"));
        when(mockViewModel.getState()).thenReturn(mockState);

        mealPlanSearchView.propertyChange(new PropertyChangeEvent(this, "Display MealPlan", null, mockState));
    }


    private JButton findButtonByName(Container container, String buttonText) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton && buttonText.equals(((JButton) comp).getText())) {
                return (JButton) comp;
            }
            if (comp instanceof Container) {
                JButton button = findButtonByName((Container) comp, buttonText);
                if (button != null) return button;
            }
        }
        return null;
    }



}
