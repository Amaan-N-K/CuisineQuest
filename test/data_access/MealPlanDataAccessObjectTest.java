package data_access;

import Entities.MealPlan;
import Entities.MealPlanDay;
import Entities.Nutrition;
import Entities.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealPlanDataAccessObjectTest {

    private MealPlanDataAccessObject mealPlanDataAccessObject;

    @Mock
    private UserDataAccessObject userDataAccessObject;
    private String identifier;
    private String startDate;
    private String endDate;
    private String diet;
    private int calorieLimit;
    private MealPlan mealPlan;
    private MealPlanDay mealPlanDay;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mealPlanDataAccessObject = new MealPlanDataAccessObject("testFilePath", userDataAccessObject);
        identifier = "2023-12-04 to 2023-12-07";
        startDate = "2023-12-07";
        endDate = "2023-12-07";
        diet = "Vegetarian";
        calorieLimit = 1000;

        List<String> ingredientsB = new ArrayList<>();
        ingredientsB.add("watermelon");
        ingredientsB.add("lemon juice");
        List<String> mealTypeB = new ArrayList<>();
        mealTypeB.add("breakfast");
        List<String> dietB = new ArrayList<>();
        dietB.add("Low-Fat");
        List<String> healthB = new ArrayList<>();
        healthB.add("Alcohol-Free");
        List<String> cuisineTypeB = new ArrayList<>();
        cuisineTypeB.add("n/a");
        Nutrition nutritionB = new Nutrition(40, 11, 0, 10,0);
        String descriptionB = "Stir it all together and serve over ice.";
        Recipe breakfast = new Recipe("id1", "WaterMelon Lemonade recipes", ingredientsB, mealTypeB, dietB, healthB, cuisineTypeB, nutritionB, descriptionB);

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
        Recipe lunch = new Recipe("id2", "WaterMelon Star Salads", ingredientsL, mealTypeL, dietL, healthL, cuisineTypeL, nutritionL, descriptionL);

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
        Recipe dinner = new Recipe("id3", "WaterMelon Pizza", ingredientsD, mealTypeD, dietD, healthD, cuisineTypeD, nutritionD, descriptionD);

        mealPlanDay = new MealPlanDay(breakfast, lunch, dinner);

        mealPlan = new MealPlan(startDate, endDate, diet, calorieLimit);
    }


    @Test
    void testSaveMealPlan() throws IOException {
        String userId = "testUser";
        MealPlan testMealPlan = new MealPlan("2021-01-01", "2021-01-07", "Balanced", 2000);

        mealPlanDataAccessObject.saveMealPlan(userId, testMealPlan);

        MealPlan loadedMealPlan = mealPlanDataAccessObject.loadMealPlan(userId);
        assertEquals(testMealPlan.getStartDate(), loadedMealPlan.getStartDate());
        assertEquals(testMealPlan.getEndDate(), loadedMealPlan.getEndDate());
        assertEquals(testMealPlan.getDiet(), loadedMealPlan.getDiet());
        assertEquals(testMealPlan.getCalorieLimit(), loadedMealPlan.getCalorieLimit());
    }


    @Test
    void testGetGrocerylist() throws IOException {
        String userId = "testUser";
        MealPlan testMealPlan = mealPlan;
        mealPlan.addMealPlanDay(mealPlanDay);
        mealPlanDataAccessObject.saveMealPlan(userId, testMealPlan);

        when(userDataAccessObject.getActive()).thenReturn(userId);

        List<String> groceryList = mealPlanDataAccessObject.getGrocerylist();

        assertTrue(groceryList.contains("watermelon"));
    }

}