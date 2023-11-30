package View;

import Entities.MealPlan;
import Entities.MealPlanDay;
// import InterfaceAdapters.grocery_list.GroceryController;
import javax.swing.*;
import java.awt.*;

public class MealPlanDisplayView {
    private MealPlan mealPlan;
    private JButton getGroceryListBotton;


    public MealPlanDisplayView(MealPlan mealPlan) {

        this.mealPlan = mealPlan;
        this.getGroceryListBotton = new JButton("Get Grocery List");
    }

    public void displayMealPlan() {
        JFrame frame = new JFrame("Meal Plan");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for showing input criteria
        JPanel criteriaPanel = new JPanel(new GridLayout(0, 2)); // 2 columns for label and value
        criteriaPanel.setBorder(BorderFactory.createTitledBorder("Meal Plan Criteria"));

        // Adding input criteria
        criteriaPanel.add(new JLabel("Meal Plan "));
        criteriaPanel.add(new JLabel(mealPlan.getIdentifier().toString()));
        criteriaPanel.add(new JLabel("Start Day:"));
        criteriaPanel.add(new JLabel(mealPlan.getStartDate().toString()));
        criteriaPanel.add(new JLabel("End Day:"));
        criteriaPanel.add(new JLabel(mealPlan.getEndDate().toString()));
        criteriaPanel.add(new JLabel("Diet:"));
        criteriaPanel.add(new JLabel(String.join(", ", mealPlan.getDiets())));
        criteriaPanel.add(new JLabel("Caloric Limit:"));
        criteriaPanel.add(new JLabel(String.valueOf(mealPlan.getCalorieLimit())));

        frame.add(criteriaPanel, BorderLayout.NORTH);

        // Panel for meal plans
        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new BoxLayout(daysPanel, BoxLayout.Y_AXIS));

        for (MealPlanDay mealPlanDay : mealPlan.getRecipes()) {
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(BorderFactory.createTitledBorder("Day"));

            // Add meal details for each day
            JLabel breakfastLabel = new JLabel("Breakfast: " + mealPlanDay.getBreakfastRecipe());
            JLabel lunchLabel = new JLabel("Lunch: " + mealPlanDay.getLunchRecipe());
            JLabel dinnerLabel = new JLabel("Dinner: " + mealPlanDay.getDinnerRecipe());

            dayPanel.add(breakfastLabel);
            dayPanel.add(lunchLabel);
            dayPanel.add(dinnerLabel);

            daysPanel.add(dayPanel);
        }

        JScrollPane scrollPane = new JScrollPane(daysPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        daysPanel.add(getGroceryListBotton);

        // getGroceryListBotton.addActionListener(e -> {
            // getListController.generateGroceryList(mealPlan);
        // });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
   // private void handleGetGroceryListAction() {
        //GroceryList groceryList = groceryListController.generateGroceryList(mealPlan); // to be implemented
}