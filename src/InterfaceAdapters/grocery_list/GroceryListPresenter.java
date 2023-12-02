package InterfaceAdapters.grocery_list;

import InterfaceAdapters.ViewManagerModel;
import UseCase.grocery_list.GroceryListOutputBoundary;
import UseCase.grocery_list.GroceryListOutputData;

public class GroceryListPresenter implements GroceryListOutputBoundary {
    private final GroceryListViewModel groceryListViewModel;
    private final ViewManagerModel viewManagerModel; // Assuming this exists in your architecture

    public GroceryListPresenter(ViewManagerModel viewManagerModel, GroceryListViewModel groceryListViewModel) {
        this.groceryListViewModel = groceryListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void present(GroceryListOutputData groceryListOutputData) {
        if (!groceryListOutputData.getUsecaseFailed()) {
            groceryListViewModel.updateGroceryList(groceryListOutputData.getGroceryList());
        }
        else {
            groceryListViewModel.errorMessage();
        }
    }


}
