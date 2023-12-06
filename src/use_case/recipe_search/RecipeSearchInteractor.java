package use_case.recipe_search;

import entities.Recipe;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeSearchInteractor implements RecipeSearchInputBoundary {
    private final RecipeSearchOutputBoundary outputBoundary;
    private final RecipeDataAccessInterface apiSearch;

    // Constructor with dependency injection for the output boundary
    public RecipeSearchInteractor(RecipeSearchOutputBoundary outputBoundary, RecipeDataAccessInterface apiSearch) {
        this.outputBoundary = outputBoundary;
        this.apiSearch = apiSearch;
    }

    @Override
    public void execute(RecipeSearchInputData inputData) {
        List<Recipe> recipes = apiSearch.searchReturnResults(inputData);

        List<RecipeSearchDTO> recipeSearchDTOList = recipes.stream()
                .map(RecipeSearchDTO::new)
                .collect(Collectors.toList());

        RecipeSearchOutputData outputData = new RecipeSearchOutputData(recipeSearchDTOList);

        outputBoundary.present(outputData);
    }

    @Override
    public void back(){
        outputBoundary.back();
    }

}