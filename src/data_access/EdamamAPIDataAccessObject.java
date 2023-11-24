package data_access;

import Entities.Recipe;
import UseCase.MealPlanCreation.MealPlanAPIDataAccessInterface;

import UseCase.MealPlanCreation.MealPlanInputData;
import okhttp3.OkHttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EdamamAPIDataAccessObject implements MealPlanAPIDataAccessInterface {
    private OkHttpClient client;
    private ObjectMapper objectMapper;
    private String appId;
    private String appKey;

    public EdamamAPIDataAccessObject(String appId, String appKey) {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
        this.appId = appId;
        this.appKey = appKey;
    }

    @Override
    public List<Recipe> findRecipes(String diets, int calorieLimit) throws IOException {
        String query = createQueryFromInputData(diets, calorieLimit);
        String url = buildUrl(query);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return parseRecipes(responseBody);
            } else {
                throw new IOException("Failed to fetch recipes: " + response);
            }
        }
    }
    private String createQueryFromInputData(String diets, int calorieLimit) {
        StringBuilder queryBuilder = new StringBuilder();
        if (diets != null) {
            queryBuilder.append("diet=").append(diets);
        }
        queryBuilder.append("calorie limit=").append(calorieLimit);
        return queryBuilder.toString();
    }

    private String buildUrl(String query) {
        return "https://api.edamam.com/search?q=" + query + "&app_id=" + appId + "&app_key=" + appKey + "&from=0&to=20";
    }

    private List<Recipe> parseRecipes(String json) throws IOException {
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode hitsNode = rootNode.path("hits");
        List<Recipe> recipes = new ArrayList<>();

        if (hitsNode.isArray()) {
            for (JsonNode hit : hitsNode) {
                Recipe recipe = objectMapper.treeToValue(hit.path("recipe"), Recipe.class);
                recipes.add(recipe);
            }
        }

        return recipes;
    }
}