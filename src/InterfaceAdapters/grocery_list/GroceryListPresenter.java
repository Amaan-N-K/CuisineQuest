package InterfaceAdapters.grocery_list;

import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.dashboard.DashboardViewModel;
import UseCase.grocery_list.GroceryListOutputBoundary;
import UseCase.grocery_list.GroceryListOutputData;

public class GroceryListPresenter implements GroceryListOutputBoundary {
    private final GroceryListViewModel groceryListViewModel;
    private final ViewManagerModel viewManagerModel;

    private final DashboardViewModel dashboardViewModel;

    public GroceryListPresenter(ViewManagerModel viewManagerModel, GroceryListViewModel groceryListViewModel, DashboardViewModel dashboardViewModel) {
        this.groceryListViewModel = groceryListViewModel;
        this.viewManagerModel = viewManagerModel;
        this.dashboardViewModel = dashboardViewModel;
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

    @Override
    public void back(){
        viewManagerModel.setActiveView(dashboardViewModel.viewName);
        viewManagerModel.firePropertyChanged();
    }


}
