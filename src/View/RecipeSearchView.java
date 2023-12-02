package View;


import InterfaceAdapters.saveFavorite.RecipeSaveController;
import InterfaceAdapters.saveFavorite.RecipeSaveState;
import InterfaceAdapters.saveFavorite.RecipeSaveViewModel;
import InterfaceAdapters.recipesearch.RecipeSearchController;
import InterfaceAdapters.recipesearch.RecipeSearchState;
import InterfaceAdapters.recipesearch.RecipeSearchViewModel;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

public class RecipeSearchView extends JPanel implements PropertyChangeListener {
    private final RecipeSearchViewModel viewModel;
    private final RecipeSearchController controller;
    private final RecipeSaveController saveController;
    private final RecipeSaveViewModel recipeSaveViewModel;

    public final String viewName = "recipe search";


    // UI Components
    private JTextField caloriesMinField;
    private JTextArea ingredientsArea;
    private JCheckBox halalCheckBox, glutenFreeCheckBox, vegetarianCheckBox, veganCheckBox, kosherCheckBox;
    private JPanel recipeDisplayPanel; // This is where recipes will be displayed


    public RecipeSearchView(RecipeSearchViewModel viewModel, RecipeSearchController controller,
                            RecipeSaveController saveController, RecipeSaveViewModel recipeSaveViewModel) {
        this.viewModel = viewModel;
        this.recipeSaveViewModel = recipeSaveViewModel;
        this.controller = controller;
        this.saveController = saveController;

        viewModel.addPropertyChangeListener(this);
        recipeSaveViewModel.addPropertyChangeListener(this);
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Recipe Search");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> controller.back());
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(backButton);

        // Calories Goal Input
        JLabel caloriesMinLabel = new JLabel("Calories Goal:");
        caloriesMinField = new JTextField(10);
        JPanel caloriesMinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        caloriesMinPanel.add(caloriesMinLabel);
        caloriesMinPanel.add(caloriesMinField);
        this.add(caloriesMinPanel);


        // Ingredients Input
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsArea = new JTextArea(3, 20);
        ingredientsArea.setLineWrap(true);
        ingredientsArea.setWrapStyleWord(true);
        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsArea);
        this.add(ingredientsLabel);
        this.add(ingredientsScrollPane);

        // Dietary Restrictions Checkboxes
        JLabel restrictionsLabel = new JLabel("Dietary Restrictions:");
        halalCheckBox = new JCheckBox("Halal");
        glutenFreeCheckBox = new JCheckBox("Gluten Free");
        vegetarianCheckBox = new JCheckBox("Vegetarian");
        veganCheckBox = new JCheckBox("Vegan");
        kosherCheckBox = new JCheckBox("Kosher");
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.add(halalCheckBox);
        checkBoxPanel.add(glutenFreeCheckBox);
        checkBoxPanel.add(vegetarianCheckBox);
        checkBoxPanel.add(veganCheckBox);
        checkBoxPanel.add(kosherCheckBox);
        this.add(restrictionsLabel);
        this.add(checkBoxPanel);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this::performSearch);
        this.add(searchButton);

        // Recipe display area
        recipeDisplayPanel = new JPanel();
        recipeDisplayPanel.setLayout(new BoxLayout(recipeDisplayPanel, BoxLayout.Y_AXIS));
        this.add(new JScrollPane(recipeDisplayPanel));
    }

    private void performSearch(ActionEvent e) {
        // Collect the calorie goal, converting the text to an integer.
        int calorieGoal = 0;
        try {
            calorieGoal = Integer.parseInt(caloriesMinField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid calorie goal.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Collect the ingredients from the text area, split by commas.
        List<String> ingredients = Arrays.asList(ingredientsArea.getText().split("\\s*,\\s*"));

        // Collect the states of the dietary restriction checkboxes.
        boolean isHalal = halalCheckBox.isSelected();
        boolean isKosher = kosherCheckBox.isSelected();
        boolean isGlutenFree = glutenFreeCheckBox.isSelected();
        boolean isVegetarian = vegetarianCheckBox.isSelected();
        boolean isVegan = veganCheckBox.isSelected();

        // Call the search method on the controller with the collected parameters.
        controller.searchRecipes(calorieGoal, ingredients, isHalal, isKosher, isGlutenFree, isVegetarian, isVegan);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // The property name to check against is updated based on the latest convention used in ViewModel
        if ("recipes".equals(evt.getPropertyName())) {
            // Update the view with the new state
            updateRecipeDisplay(viewModel.getState());
        }
        if ("state".equals(evt.getPropertyName())) {
            handleSaveStateChange(recipeSaveViewModel.getState());
        }
    }

    private void handleSaveStateChange(RecipeSaveState state) {
        if (state.isSaveSuccessful()) {
            JOptionPane.showMessageDialog(this, state.getMessage(), "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, state.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateRecipeDisplay(RecipeSearchState state) {
        recipeDisplayPanel.removeAll(); // Clear the existing recipe display
        List<String> recipeNames = state.getRecipeNames();
        List<String> recipeDescriptions = state.getRecipeDescriptions();
        List<List<String>> recipeIngredients = state.getRecipeIngredients();
        List<String> recipeIDs = state.getRecipeIDs();

        // Iterate over the names, descriptions, and ingredients to update the display
        for (int i = 0; i < recipeNames.size(); i++) {
            String name = recipeNames.get(i);
            String description = recipeDescriptions.get(i);
            List<String> ingredients = recipeIngredients.get(i);
            String recipeID = recipeIDs.get(i);

            // Create a panel for each recipe
            JPanel recipePanel = new JPanel();
            recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
            recipePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set a border for the panel

            // Add the name label
            JLabel nameLabel = new JLabel(name);
            recipePanel.add(nameLabel);

            // Add the description label
            JLabel descriptionLabel = new JLabel(description);
            recipePanel.add(descriptionLabel);

            // Create a JList for ingredients and add it to a scroll pane
            JList<String> ingredientsList = new JList<>(ingredients.toArray(new String[0]));
            JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsList);
            recipePanel.add(ingredientsScrollPane);

            // Create a favorite button and add it to the panel
            JButton favoriteButton = new JButton("Favorite");
            favoriteButton.addActionListener(e -> saveController.execute(description));
            recipePanel.add(favoriteButton);

            // Add the complete recipe panel to the display panel
            recipeDisplayPanel.add(recipePanel);
        }
        recipeDisplayPanel.revalidate();
        recipeDisplayPanel.repaint();
    }
}