package data_access;

import entities.MealPlan;
import entities.MealPlanDay;
import use_case.meal_plan_creation.MealPlanDataAccessInterface;
import use_case.grocery_list.GroceryListDataAccessInterface;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MealPlanDataAccessObject implements MealPlanDataAccessInterface, GroceryListDataAccessInterface {
    private final ObjectMapper objectMapper;
    private final String filePath;

    private final UserDataAccessObject userDataAccessObject;

    public MealPlanDataAccessObject(String filePath, UserDataAccessObject userDataAccessObject) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.userDataAccessObject = userDataAccessObject;
    }

    private Map<String, MealPlan> loadAllMealPlans() throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            return objectMapper.readValue(file, new TypeReference<Map<String, MealPlan>>() {});
        } else {
            return new HashMap<>();
        }
    }

    public void saveMealPlan(String userId, MealPlan mealPlan) throws IOException {
        Map<String, MealPlan> mealPlans = loadAllMealPlans();
        mealPlans.put(userId, mealPlan);

        File file = new File(filePath);
        objectMapper.writeValue(file, mealPlans);
    }

    public MealPlan loadMealPlan(String userId) throws IOException {
        Map<String, MealPlan> mealPlans = loadAllMealPlans();
        return mealPlans.getOrDefault(userId, null);
    }

    @Override
    public List<String> getGrocerylist() {
        String userID = userDataAccessObject.getActive();
        Set<String> uniqueIngredients = new HashSet<>();
        try {
            List<MealPlanDay> mealPlanDays = loadMealPlan(userID).getMealPlanDays();
            for (MealPlanDay mealPlanDay : mealPlanDays) {
                uniqueIngredients.addAll(mealPlanDay.getBreakfastRecipe().getIngredients());
                uniqueIngredients.addAll(mealPlanDay.getLunchRecipe().getIngredients());
                uniqueIngredients.addAll(mealPlanDay.getDinnerRecipe().getIngredients());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(uniqueIngredients);
    }
}
