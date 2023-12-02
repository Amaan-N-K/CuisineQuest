package View;

import InterfaceAdapters.grocery_list.GroceryListController;
import InterfaceAdapters.grocery_list.GroceryListViewModel;
import InterfaceAdapters.grocery_list.GroceryListState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class GroceryListView extends JPanel implements PropertyChangeListener {
    private final GroceryListViewModel viewModel;
    private final GroceryListController controller;

    public final String viewName = "grocery list";

    // UI Components
    private JTextArea groceryListTextArea;
    private JLabel mealPlanInfoLabel;
    private JButton generateListButton, backButton;

    public GroceryListView(GroceryListController controller, GroceryListViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Grocery List Generator");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Meal Plan Info
        mealPlanInfoLabel = new JLabel("Meal Plan Information: ");
        mealPlanInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(mealPlanInfoLabel);

        // Grocery List TextArea
        groceryListTextArea = new JTextArea(6, 20);
        groceryListTextArea.setEditable(false);
        add(new JScrollPane(groceryListTextArea));

        // Generate List Button
        generateListButton = new JButton("Generate Grocery List");
        generateListButton.addActionListener(this::onGenerateList);
        add(generateListButton);

//        // Back Button
//        backButton = new JButton("Back");
//        backButton.addActionListener(e -> controller.back());
//        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        add(backButton);
    }

    private void onGenerateList(ActionEvent e) {
        controller.generateGroceryList();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("groceryList".equals(evt.getPropertyName())) {
            updateGroceryList(viewModel.getState());
        } else if ("groceryError".equals(evt.getPropertyName())) {
            showErrorMessage();
        }
    }

    private void updateGroceryList(GroceryListState state) {
        List<String> groceryList = state.getGroceryList();
        StringBuilder listBuilder = new StringBuilder();

        for (String item : groceryList) {
            listBuilder.append(item).append("\n"); // Append each item followed by a newline
        }

        groceryListTextArea.setText(listBuilder.toString()); // Set the text of groceryListTextArea

    }


    private void showErrorMessage() {
        JOptionPane.showMessageDialog(this, "Error, ensure you have created a meal plan.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

}
