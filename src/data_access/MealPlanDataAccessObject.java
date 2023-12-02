package data_access;

import Entities.MealPlan;
import UseCase.MealPlanCreation.MealPlanDataAccessInterface;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MealPlanDataAccessObject implements MealPlanDataAccessInterface {
    private final ObjectMapper objectMapper;
    private final String directoryPath;

    public MealPlanDataAccessObject(String directoryPath) {
        this.directoryPath = directoryPath;
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
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
}
