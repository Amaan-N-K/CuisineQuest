package data_access;

import entities.Recipe;
import entities.RecipeFactory;
import use_case.meal_plan_creation.MealPlanAPIDataAccessInterface;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class EdamamAPIDataAccessObject implements MealPlanAPIDataAccessInterface {
    private static final String API_URL = "https://api.edamam.com/search";
    private static final String APP_ID = "ec8fb33e";
    private static final String APP_KEY = "20c75e25ffea8a5c3da3f73efdc5d0ea";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public List<Recipe> findRecipes(String diet, int calorieLimit){
        List<Recipe> recipes = new ArrayList<>();
        HttpUrl url = buildUrl(diet, calorieLimit);

        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                recipes.addAll(parseRecipes(responseBody));
            } else {
                System.err.println("API request failed with status code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipes;
    }
    private HttpUrl buildUrl(String diet, int calorieLimit) {
        int minCalories = (int) (calorieLimit * 0.75); // 75% of calorieLimit
        int maxCalories = (int) (calorieLimit * 1.25); // 125% of calorieLimit

        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder()
                .addQueryParameter("app_id", APP_ID)
                .addQueryParameter("app_key", APP_KEY)
                .addQueryParameter("q", "")
                .addQueryParameter("calories", minCalories + "-" + maxCalories);


        if (diet.contains("Balanced")){
            urlBuilder.addQueryParameter("diet", "balanced");
        }
        if (diet.contains("High-Fiber")){
            urlBuilder.addQueryParameter("diet", "high-fiber");
        }
        if (diet.contains("High-Protein")){
            urlBuilder.addQueryParameter("diet", "high-protein");
        }
        if (diet.contains("Low-Carb")){
            urlBuilder.addQueryParameter("diet", "low-carb");
        }
        if (diet.contains("Low-Fat")){
            urlBuilder.addQueryParameter("diet", "low-fat");
        }
        if (diet.contains("Low-Sodium")){
            urlBuilder.addQueryParameter("diet", "low-sodium");
        }
        if (diet.contains("Alcohol-Free")){
            urlBuilder.addQueryParameter("health", "alcohol-free");
        }
        if (diet.contains("Dairy-Free")){
            urlBuilder.addQueryParameter("health", "dairy-free");
        }
        if (diet.contains("Gluten-Free")){
            urlBuilder.addQueryParameter("health", "gluten-free");
        }
        if (diet.contains("Kosher")){
            urlBuilder.addQueryParameter("health", "kosher");
        }
        if (diet.contains("Pork-Free")){
            urlBuilder.addQueryParameter("health", "pork_free");
        }
        if (diet.contains("Vegan")){
            urlBuilder.addQueryParameter("health", "vegan");
        }
        if (diet.contains("Vegetarian")){
            urlBuilder.addQueryParameter("health", "vegetarian");
        }

        return urlBuilder.build();
    }

    private List<Recipe> parseRecipes(String json) throws IOException {
        List<Recipe> recipes = new ArrayList<>();
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode hits = rootNode.path("hits");

        for (JsonNode hit : hits) {
            JsonNode recipeNode = hit.path("recipe");
            Recipe recipe = createRecipeFromNode(recipeNode);
            recipes.add(recipe);
        }

        return recipes;
    }

    private Recipe createRecipeFromNode(JsonNode recipeNode) {
        String recipeId = recipeNode.path("uri").asText();
        String name = recipeNode.path("label").asText();
        String description = recipeNode.path("url").asText();

        List<String> ingredients = new ArrayList<>();
        recipeNode.path("ingredientLines").forEach(ingredient -> ingredients.add(ingredient.asText()));

        List<String> mealType = new ArrayList<>();
        recipeNode.path("mealType").forEach(mt -> mealType.add(mt.asText()));

        List<String> diet = new ArrayList<>();
        recipeNode.path("dietLabels").forEach(dl -> diet.add(dl.asText()));

        List<String> health = new ArrayList<>();
        recipeNode.path("healthLabels").forEach(hl -> health.add(hl.asText()));

        List<String> cuisineType = new ArrayList<>();
        recipeNode.path("cuisineType").forEach(ct -> cuisineType.add(ct.asText()));

        JsonNode nutrients = recipeNode.path("totalNutrients");
        int calorie = nutrients.path("ENERC_KCAL").path("quantity").asInt();
        int carbohydrates = nutrients.path("CHOCDF").path("quantity").asInt();
        int protein = nutrients.path("PROCNT").path("quantity").asInt();
        int sugar = nutrients.path("SUGAR").path("quantity").asInt();
        int fiber = nutrients.path("FIBTG").path("quantity").asInt();

        return new RecipeFactory().create(recipeId, name, ingredients, mealType, diet, health, cuisineType, description, calorie, carbohydrates, protein, sugar, fiber);
    }
}