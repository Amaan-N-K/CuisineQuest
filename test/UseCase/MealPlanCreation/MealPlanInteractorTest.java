package UseCase.MealPlanCreation;
import Entities.MealPlan;
import Entities.MealPlanDay;
import Entities.Nutrition;
import Entities.Recipe;
import data_access.UserDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class MealPlanInteractorTest {

    @Mock
    private MealPlanAPIDataAccessInterface mockApiAccess;
    @Mock
    private MealPlanOutputBoundary mockPresenter;
    @Mock
    private MealPlanDataAccessInterface mockDataAccess;
    @Mock
    private UserDataAccessObject mockUserDataAccess;

    private MealPlanInteractor interactor;
    private Recipe breakfast;
    private Recipe lunch;
    private Recipe dinner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        interactor = new MealPlanInteractor(mockApiAccess, mockPresenter, mockDataAccess, mockUserDataAccess);
        List<String> ingredientsB = new ArrayList<>();
        ingredientsB.add("watermelon");
        ingredientsB.add("lemon juice");
        List<String> mealTypeB = new ArrayList<>();
        mealTypeB.add("breakfast");
        List<String> dietB = new ArrayList<>();
        dietB.add("Low-Fat");
        List<String> healthB = new ArrayList<>();
        healthB.add("Vegetarian");
        List<String> cuisineTypeB = new ArrayList<>();
        cuisineTypeB.add("n/a");
        Nutrition nutritionB = new Nutrition(40, 11, 0, 10,0);
        String descriptionB = "Stir it all together and serve over ice.";
        breakfast = new Recipe("id1", "WaterMelon Lemonade recipes", ingredientsB, mealTypeB, dietB, healthB, cuisineTypeB, nutritionB, descriptionB);

        List<String> ingredientsL = new ArrayList<>();
        ingredientsL.add("watermelon");
        ingredientsL.add("lettuce");
        List<String> mealTypeL = new ArrayList<>();
        mealTypeL.add("lunch");
        List<String> dietL = new ArrayList<>();
        dietL.add("Vegetarian");
        List<String> healthL = new ArrayList<>();
        healthL.add("Balanced");
        List<String> cuisineTypeL = new ArrayList<>();
        cuisineTypeL.add("n/a");
        Nutrition nutritionL = new Nutrition(164, 17, 14, 14,1);
        String descriptionL = "Mix it all together.";
        lunch = new Recipe("id2", "WaterMelon Star Salads", ingredientsL, mealTypeL, dietL, healthL, cuisineTypeL, nutritionL, descriptionL);

        List<String> ingredientsD = new ArrayList<>();
        ingredientsD.add("watermelon");
        ingredientsD.add("onion");
        List<String> mealTypeD = new ArrayList<>();
        mealTypeD.add("dinner");
        List<String> dietD = new ArrayList<>();
        dietD.add("Vegetarian");
        List<String> healthD = new ArrayList<>();
        healthD.add("Gluten-Free");
        List<String> cuisineTypeD = new ArrayList<>();
        cuisineTypeD.add("n/a");
        Nutrition nutritionD = new Nutrition(258, 41, 6, 29,4);
        String descriptionD = "Slice the watermelon and top with onion.";
        dinner = new Recipe("id3", "WaterMelon Pizza", ingredientsD, mealTypeD, dietD, healthD, cuisineTypeD, nutritionD, descriptionD);
    }

    @Test
    void createMealPlan_ValidDateRange_Success() throws IOException {
        MealPlanInputData inputData = new MealPlanInputData("2023-06-01", "2023-06-01", "Vegetarian", 2000);
        List<Recipe> mockRecipes = Arrays.asList(breakfast, lunch, dinner);
        when(mockApiAccess.findRecipes(anyString(), anyInt())).thenReturn(mockRecipes);
        when(mockUserDataAccess.getActive()).thenReturn("user123");

        interactor.createMealPlan(inputData);

        verify(mockApiAccess).findRecipes("Vegetarian", 2000);
        verify(mockDataAccess).saveMealPlan(eq("user123"), any(MealPlan.class));
        verify(mockPresenter).presentMealPlan(any(MealPlanOutputData.class));
    }

    @Test
    void createMealPlan_InvalidDateRange_Failure() {
        MealPlanInputData inputData = new MealPlanInputData("2023-06-07", "2023-06-01", "Vegetarian", 2000);

        interactor.createMealPlan(inputData);

        verify(mockPresenter).prepareFailView("Invalid dates.");
        verifyNoMoreInteractions(mockApiAccess, mockDataAccess, mockPresenter);
    }
    @Test
    void back() {
        interactor.back();
        verify(mockPresenter).back();
    }
}