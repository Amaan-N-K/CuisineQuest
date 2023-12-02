package app;

import InterfaceAdapters.grocery_list.GroceryListController;
import InterfaceAdapters.grocery_list.GroceryListPresenter;
import InterfaceAdapters.grocery_list.GroceryListViewModel;
import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.dashboard.DashboardViewModel;
import UseCase.grocery_list.GroceryListDataAccessInterface;
import UseCase.grocery_list.GroceryListInputBoundary;
import UseCase.grocery_list.GroceryListInteractor;
import UseCase.grocery_list.GroceryListOutputBoundary;
import View.GroceryListView;

public class GroceryListUseCaseFactory {
    private GroceryListUseCaseFactory() {}

    private static GroceryListController createGroceryListController(GroceryListViewModel groceryListViewModel, DashboardViewModel dashboardViewModel,
                                                                     GroceryListDataAccessInterface groceryListDataAccessObject, ViewManagerModel viewManagerModel) {

        GroceryListOutputBoundary groceryListPresenter = new GroceryListPresenter(viewManagerModel, groceryListViewModel);

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
