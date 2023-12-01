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

    @Override
    public void saveMealPlan(MealPlan mealPlan) throws IOException {
        File file = new File(directoryPath, mealPlan.getIdentifier() + ".json");
        objectMapper.writeValue(file, mealPlan);
    }

    @Override
    public MealPlan loadMealPlan(String identifier) throws IOException {
        File file = new File(directoryPath, identifier + ".json");
        if (!file.exists()) {
            return null;
        }
        return objectMapper.readValue(file, MealPlan.class);
    }

}
