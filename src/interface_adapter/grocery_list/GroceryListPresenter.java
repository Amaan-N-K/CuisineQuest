package interface_adapter.grocery_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.grocery_list.GroceryListOutputBoundary;
import use_case.grocery_list.GroceryListOutputData;

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
