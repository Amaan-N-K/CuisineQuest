package view;

import interface_adapter.view_favorites.ViewFavoritesController;
import interface_adapter.dashboard.DashboardController;
//import InterfaceAdapters.dashboard.DashboardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardView extends JPanel {

    public final String viewName = "dashboard";
    private DashboardController dashboardController;
    private ViewFavoritesController viewFavoritesController;
//    private DashboardViewModel dashboardViewModel;

    public DashboardView(DashboardController dashboardController, ViewFavoritesController viewFavoritesController) {
        this.dashboardController = dashboardController;
        this.viewFavoritesController = viewFavoritesController;
//        this.dashboardViewModel = dashboardViewModel;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome To CuisineQuest!");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Create buttons
        add(createButton("GroceryList"));
        add(createButton("MealPlan"));
        add(createButton("RecipeSearch"));
        add(createFavoritesButton());

    }

    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboardController.execute(name);
            }
        });
        return button;
    }
    private JButton createFavoritesButton() {
        JButton button = new JButton("FavouriteRecipes");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> viewFavoritesController.execute());
        return button;
    }
}
