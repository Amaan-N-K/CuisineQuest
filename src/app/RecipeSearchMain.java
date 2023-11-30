package app;

import InterfaceAdapters.RecipeSearchViewModel;
import View.RecipeSearchView;
import data_access.RecipeSearchAPIDataAccessObject;

import javax.swing.*;

public class RecipeSearchMain {
    public static void main(String[] args) {
        // The main application window.
        JFrame application = new JFrame("Recipe Search");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ViewModel for the recipe search view
        RecipeSearchViewModel recipeSearchViewModel = new RecipeSearchViewModel();

        // Assuming RecipeSearchAPIDataAccessObject requires no arguments in constructor,
        // otherwise, provide necessary configuration.
        RecipeSearchAPIDataAccessObject recipeDataAccessObject = new RecipeSearchAPIDataAccessObject();

        // Use the RecipeSearchUseCaseFactory to create the recipe search view
        RecipeSearchView recipeSearchView = RecipeSearchUseCaseFactory.createRecipeSearchView(
                recipeSearchViewModel,
                recipeDataAccessObject
        );

        // Add the view to the main application frame
        application.add(recipeSearchView);

        application.pack();
        application.setVisible(true);
    }
}