package data_access;

import Entities.MealPlan;
import Entities.MealPlanDay;
import UseCase.MealPlanCreation.MealPlanDataAccessInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import UseCase.grocery_list.GroceryListDataAccessInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MealPlanDataAccessObject implements MealPlanDataAccessInterface, GroceryListDataAccessInterface {
    private final UserDataAccessObject userDataAccessObject;
    private final ObjectMapper objectMapper;
    private final String directoryPath;

    public MealPlanDataAccessObject(String directoryPath, UserDataAccessObject userDataAccessObject) {
        this.directoryPath = directoryPath;
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.userDataAccessObject = userDataAccessObject;
    }

    public void saveMealPlan(String userId, MealPlan mealPlan) throws IOException {
        String userDirectoryPath = directoryPath + File.separator + userId;
        File userDirectory = new File(userDirectoryPath);
        if (!userDirectory.exists()) {
            userDirectory.mkdirs();
        }

        // Save using userId as the filename, assuming each user has only one meal plan
        File file = new File(userDirectory, userId + ".json");
        objectMapper.writeValue(file, mealPlan);
    }

    public MealPlan loadMealPlan(String userId) throws IOException {
        File file = new File(directoryPath + File.separator + userId, userId + ".json");
        if (!file.exists()) {
            return null;
        }
        return objectMapper.readValue(file, MealPlan.class);
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
