package InterfaceAdapters.recipesearch;

import Entities.Recipe;
import UseCase.recipesearch.RecipeSearchInputData;
import data_access.RecipeSearchAPIDataAccessObject;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RecipeSearchAPIDataAccessObjectTest {

    @Test
    void testSearchReturnResultsWithValidResponse() throws IOException {
        // Arrange
        OkHttpClient client = mock(OkHttpClient.class);
        Call call = mock(Call.class);
        Response response = new Response.Builder()
                .code(200) // HTTP OK
                .message("OK")
                .protocol(Protocol.HTTP_1_1)
                .request(new Request.Builder().url("http://dummyurl.com").build())
                .body(ResponseBody.create(MediaType.get("application/json"), "[{\"recipeId\":\"1\"}]"))
                .build();

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);

        RecipeSearchAPIDataAccessObject dao = new RecipeSearchAPIDataAccessObject();
        RecipeSearchInputData inputData = new RecipeSearchInputData(500, Arrays.asList("Chicken", "Rice"), false, false, false, false, false);

        // Act
        List<Recipe> result = dao.searchReturnResults(inputData);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());

    }

}
