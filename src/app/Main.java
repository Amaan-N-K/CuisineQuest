package app;

import entities.UserFactory;
import interface_adapter.login.LogInViewModel;
import interface_adapter.meal_plan_creation.MealPlanViewModel;
import interface_adapter.sign_up.SignUpViewModel;
import interface_adapter.view_favorites.ViewFavoritesController;
import interface_adapter.view_favorites.ViewFavoritesViewModel;
import interface_adapter.dashboard.DashboardViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import interface_adapter.save_favorite.RecipeSaveViewModel;
import view.*;
import data_access.EdamamAPIDataAccessObject;
import data_access.MealPlanDataAccessObject;
import data_access.RecipeSearchAPIDataAccessObject;
import data_access.UserDataAccessObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // The main application window.
        JFrame application = new JFrame("CuisineQuest");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        RecipeSearchViewModel recipeSearchViewModel = new RecipeSearchViewModel();
        DashboardViewModel dashboardViewModel = new DashboardViewModel();
        GroceryListViewModel groceryListViewModel = new GroceryListViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignUpViewModel signUpViewModel = new SignUpViewModel();
        LogInViewModel logInViewModel = new LogInViewModel();
        RecipeSaveViewModel recipeSaveViewModel = new RecipeSaveViewModel();
        MealPlanViewModel mealPlanViewModel = new MealPlanViewModel();
        ViewFavoritesViewModel viewFavoritesViewModel = new ViewFavoritesViewModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        RecipeSearchAPIDataAccessObject recipeDataAccessObject = new RecipeSearchAPIDataAccessObject();
        EdamamAPIDataAccessObject edamamAPIDataAccessObject = new EdamamAPIDataAccessObject();


        String csvPath = "src" + File.separator + "users.csv";
        String jsonPath = "src" + File.separator + "mealPlans.json";
        UserFactory userFactory = new UserFactory();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(csvPath, userFactory);
        MealPlanDataAccessObject mealPlanDataAccessObject = new MealPlanDataAccessObject(jsonPath, userDataAccessObject);


        RecipeSearchView recipeSearchView = RecipeSearchUseCaseFactory.createRecipeSearchView(
                recipeSearchViewModel,dashboardViewModel,
                recipeDataAccessObject, viewManagerModel,
                recipeSaveViewModel, userDataAccessObject
        );
        views.add(recipeSearchView, recipeSearchView.viewName);
        recipeSearchViewModel.addPropertyChangeListener(recipeSearchView);

        ViewFavoritesController viewFavoritesController = ViewFavoritesUseCaseFactory.createViewFavoritesController(viewManagerModel, viewFavoritesViewModel, userDataAccessObject);

        DashboardView dashboardView = DashboardUseCaseFactory.createDashboardView(recipeSearchViewModel, viewManagerModel, groceryListViewModel, mealPlanViewModel, viewFavoritesController);
        views.add(dashboardView, dashboardView.viewName);

        SignUpView signUpView = SignupUseCaseFactory.create(viewManagerModel, logInViewModel, signUpViewModel, userDataAccessObject);
        views.add(signUpView, signUpView.viewName);

        LogInView logInView = LoginUseCaseFactory.create(viewManagerModel, logInViewModel, userDataAccessObject);
        views.add(logInView, logInView.viewName);

        MealPlanSearchView mealPlanSearchView = MealPlanUseCaseFactory.createMealPlanView(mealPlanViewModel, dashboardViewModel, edamamAPIDataAccessObject, viewManagerModel, mealPlanDataAccessObject, userDataAccessObject);
        views.add(mealPlanSearchView, mealPlanSearchView.viewName);

        GroceryListView groceryListView = GroceryListUseCaseFactory.createGroceryListView(groceryListViewModel, dashboardViewModel, mealPlanDataAccessObject, viewManagerModel);
        views.add(groceryListView, groceryListView.viewName);
        groceryListViewModel.addPropertyChangeListener(groceryListView);

        ViewFavoritesView viewFavoritesView = ViewFavoritesUseCaseFactory.create(viewManagerModel, viewFavoritesViewModel);
        views.add(viewFavoritesView, viewFavoritesView.viewName);
        viewManagerModel.setActiveView(signUpView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}