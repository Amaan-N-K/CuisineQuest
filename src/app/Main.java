package app;

import Entities.UserFactory;
import InterfaceAdapters.LogIn.LogInViewModel;
import InterfaceAdapters.MealPlanCreation.MealPlanViewModel;
import InterfaceAdapters.SignUp.SignUpViewModel;
import InterfaceAdapters.dashboard.DashboardViewModel;
import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.grocery_list.GroceryListViewModel;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;
import InterfaceAdapters.saveFavorite.RecipeSaveViewModel;
import UseCase.MealPlanCreation.MealPlanAPIDataAccessInterface;
import View.*;
import data_access.EdamamAPIDataAccessObject;
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
        new ViewManager(views, cardLayout, viewManagerModel);

        RecipeSearchAPIDataAccessObject recipeDataAccessObject = new RecipeSearchAPIDataAccessObject();
        EdamamAPIDataAccessObject edamamAPIDataAccessObject = new EdamamAPIDataAccessObject();


        String csvPath = "src" + File.separator + "users.csv";
        UserFactory userFactory = new UserFactory();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(csvPath, userFactory);


        RecipeSearchView recipeSearchView = RecipeSearchUseCaseFactory.createRecipeSearchView(
                recipeSearchViewModel,dashboardViewModel,
                recipeDataAccessObject, viewManagerModel,
                recipeSaveViewModel, userDataAccessObject
        );
        views.add(recipeSearchView, recipeSearchView.viewName);

        DashboardView dashboardView = DashboardUseCaseFactory.createDashboardView(recipeSearchViewModel, viewManagerModel, groceryListViewModel, mealPlanViewModel);
        views.add(dashboardView, dashboardView.viewName);

        SignUpView signUpView = SignupUseCaseFactory.create(viewManagerModel, logInViewModel, signUpViewModel, userDataAccessObject);
        views.add(signUpView, signUpView.viewName);

        LogInView logInView = LoginUseCaseFactory.create(viewManagerModel, logInViewModel, userDataAccessObject);
        views.add(logInView, logInView.viewName);

        MealPlanSearchView mealPlanSearchView = MealPlanUseCaseFactory.createMealPlanView(mealPlanViewModel, dashboardViewModel, edamamAPIDataAccessObject, viewManagerModel);
        views.add(mealPlanSearchView, mealPlanSearchView.viewName);

        viewManagerModel.setActiveView(signUpView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}