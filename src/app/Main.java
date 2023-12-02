package app;

import InterfaceAdapters.dashboard.DashboardViewModel;
import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.grocery_list.GroceryListViewModel;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;
import View.DashboardView;
import View.RecipeSearchView;
import View.ViewManager;
import data_access.RecipeSearchAPIDataAccessObject;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        RecipeSearchViewModel recipeSearchViewModel = new RecipeSearchViewModel();
        DashboardViewModel dashboardViewModel = new DashboardViewModel();
        GroceryListViewModel groceryListViewModel = new GroceryListViewModel();
        RecipeSearchAPIDataAccessObject recipeDataAccessObject = new RecipeSearchAPIDataAccessObject();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        RecipeSearchView recipeSearchView = RecipeSearchUseCaseFactory.createRecipeSearchView(
                recipeSearchViewModel,dashboardViewModel,
                recipeDataAccessObject, viewManagerModel
        );
        views.add(recipeSearchView, recipeSearchView.viewName);

        DashboardView dashboardView = DashboardUseCaseFactory.createDashboardView(recipeSearchViewModel, viewManagerModel, groceryListViewModel);
        views.add(dashboardView, dashboardView.viewName);

        viewManagerModel.setActiveView(dashboardView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}