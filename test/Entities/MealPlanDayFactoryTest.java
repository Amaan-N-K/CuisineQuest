package Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MealPlanDayFactoryTest {
    private Recipe breakfast;
    private Recipe lunch;
    private Recipe dinner;

    @BeforeEach
    void setUp() {
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
    void createMealPlanDay() {
        MealPlanDayFactory mealPlanDayFactory = new MealPlanDayFactory();
        MealPlanDay mealPlanDay = mealPlanDayFactory.createMealPlanDay(breakfast, lunch, dinner);
        assertNotNull(mealPlanDay);
        assertEquals(breakfast, mealPlanDay.getBreakfastRecipe());
        assertEquals(lunch, mealPlanDay.getLunchRecipe());
        assertEquals(dinner, mealPlanDay.getDinnerRecipe());
    }
}