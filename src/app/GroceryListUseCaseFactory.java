package app;

import interface_adapter.grocery_list.GroceryListController;
import interface_adapter.grocery_list.GroceryListPresenter;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.grocery_list.GroceryListDataAccessInterface;
import use_case.grocery_list.GroceryListInputBoundary;
import use_case.grocery_list.GroceryListInteractor;
import use_case.grocery_list.GroceryListOutputBoundary;
import view.GroceryListView;

public class GroceryListUseCaseFactory {
    private GroceryListUseCaseFactory() {}

    private static GroceryListController createGroceryListController(GroceryListViewModel groceryListViewModel, DashboardViewModel dashboardViewModel,
                                                                     GroceryListDataAccessInterface groceryListDataAccessObject, ViewManagerModel viewManagerModel) {

        GroceryListOutputBoundary groceryListPresenter = new GroceryListPresenter(viewManagerModel, groceryListViewModel, dashboardViewModel);

        GroceryListInputBoundary groceryListInteractor = new GroceryListInteractor(groceryListDataAccessObject, groceryListPresenter);

        return new GroceryListController(groceryListInteractor);
    }

    public static GroceryListView createGroceryListView(
            GroceryListViewModel groceryListViewModel,
            DashboardViewModel dashboardViewModel,
            GroceryListDataAccessInterface groceryListDataAccessObject, ViewManagerModel viewManagerModel) {

        GroceryListController groceryListController = createGroceryListController(groceryListViewModel, dashboardViewModel, groceryListDataAccessObject, viewManagerModel);

        return new GroceryListView(groceryListController, groceryListViewModel);
    }
}
