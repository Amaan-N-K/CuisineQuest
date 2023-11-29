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
        ingredientsB.add("watermelon");
        ingredientsB.add("lettuce");
        List<String> mealTypeL = new ArrayList<>();
        mealTypeB.add("lunch");
        List<String> dietL = new ArrayList<>();
        dietB.add("Vegetarian");
        List<String> healthL = new ArrayList<>();
        healthB.add("Balanced");
        List<String> cuisineTypeL = new ArrayList<>();
        cuisineTypeB.add("n/a");
        Nutrition nutritionL = new Nutrition(164, 17, 14, 14,1);
        String descriptionL = "Mix it all together.";
        lunch = new Recipe("id2", "WaterMelon Star Salads", ingredientsL, mealTypeL, dietL, healthL, cuisineTypeL, nutritionL, descriptionL);

        List<String> ingredientsD = new ArrayList<>();
        ingredientsB.add("watermelon");
        ingredientsB.add("onion");
        List<String> mealTypeD = new ArrayList<>();
        mealTypeB.add("dinner");
        List<String> dietD = new ArrayList<>();
        dietB.add("Vegetarian");
        List<String> healthD = new ArrayList<>();
        healthB.add("Gluten-Free");
        List<String> cuisineTypeD = new ArrayList<>();
        cuisineTypeB.add("n/a");
        Nutrition nutritionD = new Nutrition(258, 41, 6, 29,4);
        String descriptionD = "Slice the watermelon and top with onion.";
        dinner = new Recipe("id3", "WaterMelon Pizza", ingredientsD, mealTypeD, dietD, healthD, cuisineTypeD, nutritionD, descriptionD);

    }

    @Test
    void createMealPlanDay() {
        MealPlanDayFactory mealPlanDayFactory = new MealPlanDayFactory();
        MealPlanDay mealPlanDay = mealPlanDayFactory.createMealPlanDay(breakfast, lunch, dinner);
        assertNotNull(mealPlanDay);
        assertSame(breakfast, mealPlanDay.getBreakfastRecipe());
        assertSame(lunch, mealPlanDay.getLunchRecipe());
        assertSame(dinner, mealPlanDay.getDinnerRecipe());
    }
}