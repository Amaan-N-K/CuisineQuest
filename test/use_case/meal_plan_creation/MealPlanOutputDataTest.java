package use_case.meal_plan_creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class MealPlanOutputDataTest {

    private MealPlanOutputData outputData;
    private List<String> breakfastNames;
    private List<String> breakfastDescriptions;
    private List<String> lunchNames;
    private List<String> lunchDescriptions;
    private List<String> dinnerNames;
    private List<String> dinnerDescriptions;

    @BeforeEach
    void setUp() {
        breakfastNames = Arrays.asList("Pancakes", "Oatmeal");
        breakfastDescriptions = Arrays.asList("Fluffy pancakes", "Healthy oatmeal");
        lunchNames = Arrays.asList("Sandwich", "Salad");
        lunchDescriptions = Arrays.asList("Tasty sandwich", "Fresh salad");
        dinnerNames = Arrays.asList("Pasta", "Soup");
        dinnerDescriptions = Arrays.asList("Italian pasta", "Warm soup");

        outputData = new MealPlanOutputData(
                breakfastNames, breakfastDescriptions,
                lunchNames, lunchDescriptions,
                dinnerNames, dinnerDescriptions
        );
    }

    @Test
    void getBreakfastNames_ReturnsCorrectData() {
        assertEquals(breakfastNames, outputData.getBreakfastNames());
    }

    @Test
    void getBreakfastDescriptions_ReturnsCorrectData() {
        assertEquals(breakfastDescriptions, outputData.getBreakfastDescriptions());
    }

    @Test
    void getLunchNames_ReturnsCorrectData() {
        assertEquals(lunchNames, outputData.getLunchNames());
    }

    @Test
    void getLunchDescriptions_ReturnsCorrectData() {
        assertEquals(lunchDescriptions, outputData.getLunchDescriptions());
    }

    @Test
    void getDinnerNames_ReturnsCorrectData() {
        assertEquals(dinnerNames, outputData.getDinnerNames());
    }

    @Test
    void getDinnerDescriptions_ReturnsCorrectData() {
        assertEquals(dinnerDescriptions, outputData.getDinnerDescriptions());
    }
}