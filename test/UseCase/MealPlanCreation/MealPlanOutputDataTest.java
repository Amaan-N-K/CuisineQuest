package UseCase.MealPlanCreation;

import Entities.MealPlan;
import Entities.MealPlanDay;
import Entities.Nutrition;
import Entities.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MealPlanOutputDataTest {

    private String identifier;
    private String startDate;
    private String endDate;
    private String diet;
    private int calorieLimit;
    private MealPlan mealPlan;
    private MealPlanDay mealPlanDay;

    @BeforeEach
    void setUp() {
        identifier = "2023-12-04 to 2023-12-07";
        startDate = "2023-12-04";
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
        Nutrition nutritionB = new Nutrition(40, 11, 0, 10, 0);
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
        Nutrition nutritionL = new Nutrition(164, 17, 14, 14, 1);
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
        Nutrition nutritionD = new Nutrition(258, 41, 6, 29, 4);
        String descriptionD = "Slice the watermelon and top with onion.";
        Recipe dinner = new Recipe("id3", "WaterMelon Pizza", ingredientsD, mealTypeD, dietD, healthD, cuisineTypeD, nutritionD, descriptionD);

        mealPlanDay = new MealPlanDay(breakfast, lunch, dinner);

        mealPlan = new MealPlan(startDate, endDate, diet, calorieLimit);
    }


    @Test
    void getMealPlan() {
        MealPlanOutputData outputData = new MealPlanOutputData(mealPlan);

        assertSame(mealPlan, outputData.getMealPlan(), "The meal plan returned should be the one that was set.");
    }
}