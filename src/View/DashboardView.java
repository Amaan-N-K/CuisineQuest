package View;

import InterfaceAdapters.dashboard.DashboardController;
//import InterfaceAdapters.dashboard.DashboardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardView extends JPanel {

    public final String viewName = "dashboard";
    private DashboardController dashboardController;
//    private DashboardViewModel dashboardViewModel;

    public DashboardView(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
//        this.dashboardViewModel = dashboardViewModel;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome To CuisineQuest!");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Create buttons
        add(createButton("Grocery"));
        add(createButton("Mealplan"));
        add(createButton("RecipeSearch"));
        add(createButton("FavouriteRecipes"));
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
}
