package data_access;

import Entities.NutritionFactory;
import Entities.RecipeFactory;
import UseCase.RecipeDataAccessInterface;
import UseCase.RecipeSearchInputData;
import Entities.Recipe;
import Entities.Nutrition;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearchAPIDataAccessObject implements RecipeDataAccessInterface {
    private static final String API_URL = "https://api.edamam.com/search";
    private static final String APP_ID = "a00283fc";
    private static final String APP_KEY = "774a0bb3766d16e273b58303a97b12f8";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Recipe> searchReturnResults(RecipeSearchInputData searchData) {
        List<Recipe> recipes = new ArrayList<>();
        HttpUrl url = buildUrl(searchData);

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

    private HttpUrl buildUrl(RecipeSearchInputData searchData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder()
                .addQueryParameter("app_id", APP_ID)
                .addQueryParameter("app_key", APP_KEY)
                .addQueryParameter("q", String.join(",", searchData.getIngredients()))
                .addQueryParameter("calories", searchData.getCalorieGoal() + "+");

        if (searchData.isHalal()) {
            urlBuilder.addQueryParameter("health", "pork-free");
        }
        if (searchData.isKosher()) {
            urlBuilder.addQueryParameter("health", "kosher");
        }
        if (searchData.isVegan()) {
            urlBuilder.addQueryParameter("health", "vegan");
        }
        if (searchData.isVegetarian()) {
            urlBuilder.addQueryParameter("health", "vegetarian");
        }
        if (searchData.isGlutenFree()) {
            urlBuilder.addQueryParameter("health", "gluten-free");
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
        String description = recipeNode.path("url").asText(); // URL to the source recipe

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
