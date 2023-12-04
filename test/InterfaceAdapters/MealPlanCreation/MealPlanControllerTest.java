package InterfaceAdapters.MealPlanCreation;

import UseCase.MealPlanCreation.MealPlanInputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class MealPlanControllerTest {
    private MealPlanInputBoundary interactorMock;
    private MealPlanController controller;

    @BeforeEach
    void setUp() {
        interactorMock = mock(MealPlanInputBoundary.class);
        controller = new MealPlanController(interactorMock);
    }

    @Test
    void createMealPlan() {
        String startDate = "2023-01-01";
        String endDate = "2023-01-07";
        String diet = "Vegan";
        int calorieLimit = 2000;

        controller.createMealPlan(startDate, endDate, diet, calorieLimit);

        verify(interactorMock).createMealPlan(argThat(mealPlanInputData ->
                mealPlanInputData.getStartDate().equals(startDate) &&
                        mealPlanInputData.getEndDate().equals(endDate) &&
                        mealPlanInputData.getDiet().equals(diet) &&
                        mealPlanInputData.getCalorieLimit() == calorieLimit
        ));
    }

    @Test
    void back() {
        controller.back();
        verify(interactorMock).back();
    }
}