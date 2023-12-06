package use_case.recipe_search;

import java.util.List;

public class RecipeSearchOutputData {
    public List<RecipeSearchDTO> recipeSearchDTOList;

    public RecipeSearchOutputData (List<RecipeSearchDTO> recipeSearchDTOList) {
        this.recipeSearchDTOList = recipeSearchDTOList;
    }

    public List<RecipeSearchDTO> getRecipeSearchDTOList(){
        return recipeSearchDTOList;
    }
}

