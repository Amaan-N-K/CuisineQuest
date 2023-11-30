package InterfaceAdapters;

import UseCase.RecipeSearchOutputBoundary;
import UseCase.RecipeSearchOutputData;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {
    private final RecipeSearchViewModel viewModel;

    public RecipeSearchPresenter(RecipeSearchViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(RecipeSearchOutputData outputData) {
        viewModel.presentRecipes(outputData.getRecipeSearchDTOList());
    }
}
