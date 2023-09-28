import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class testAPI {

    private static final String API_URL = "https://api.edamam.com/search?q=chicken&app_id=a00283fc&app_key=774a0bb3766d16e273b58303a97b12f8&from=0&to=1";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(API_URL).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                parseAndPrintIngredients(responseBody);
            } else {
                System.err.println("Request failed with status code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseAndPrintIngredients(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);

            // Navigate to the 'ingredientLines' node.
            JsonNode ingredientLinesNode = rootNode.path("hits").get(0).path("recipe").path("ingredientLines");

            // If the 'ingredientLines' node is found and it's an array, print each ingredient.
            if (ingredientLinesNode.isArray()) {
                for (JsonNode ingredient : ingredientLinesNode) {
                    System.out.println(ingredient.asText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
