package View;


import Entities.MealPlan;
import Entities.MealPlanDay;

import InterfaceAdapters.MealPlanCreation.MealPlanController;
import InterfaceAdapters.MealPlanCreation.MealPlanViewModel;
import InterfaceAdapters.MealPlanCreation.MealPlanState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MealPlanSearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private MealPlanViewModel mealPlanViewModel;
    private MealPlanController mealPlanController;
    public final String viewName = "meal plan";

    // UI Components
    private JTextField startDateField, endDateField;
    private List<JCheckBox> dietCheckBoxes;
    private List<JCheckBox> healthCheckBoxes;
    private JSpinner calorieSpinner;
    private JPanel mealPlanDisplayPanel;




    public MealPlanSearchView(MealPlanController mealPlanController, MealPlanViewModel mealPlanViewModel) {

        this.mealPlanController = mealPlanController;
        this.mealPlanViewModel = mealPlanViewModel;
        mealPlanViewModel.addPropertyChangeListener(this);
        initializeGUI();
    }

    public void initializeGUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Create Your Meal Plan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Date range selection
        startDateField = new JTextField(10);
        JLabel startDateLabel = new JLabel("Start Date:");
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDatePanel.add(startDateLabel);
        startDatePanel.add(startDateField);
        this.add(startDatePanel);

        endDateField = new JTextField(10);
        JLabel endDateLabel = new JLabel("End Date:");
        JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        endDatePanel.add(endDateLabel);
        endDatePanel.add(endDateField);
        this.add(endDatePanel);

        // Diet Types as Checkboxes
        JLabel dietTypeLabel = new JLabel("Diet Types:");
        String[] dietTypes = { "Balanced", "High-Fiber", "High-Protein", "Low-Carb", "Low-Fat", "Low-Sodium" };
        JPanel dietPanel = new JPanel();
        dietCheckBoxes = new ArrayList<>();
        for (String diet : dietTypes) {
            JCheckBox checkBox = new JCheckBox(diet);
            dietCheckBoxes.add(checkBox);
            dietPanel.add(checkBox);
        }
        this.add(dietTypeLabel);
        this.add(dietPanel);

        // Health Labels as Checkboxes
        JLabel healthLabel = new JLabel("Health Labels:");
        String[] healthOptions = {"Alcohol-Free", "Dairy-Free", "Gluten-Free", "Kosher", "Pork-Free", "Vegan", "Vegetarian"};
        JPanel healthPanel = new JPanel();
        healthCheckBoxes = new ArrayList<>();
        for (String health : healthOptions) {
            JCheckBox checkBox = new JCheckBox(health);
            healthCheckBoxes.add(checkBox);
            healthPanel.add(checkBox);
        }
        this.add(healthLabel);
        this.add(healthPanel);


        // Calorie limit
        calorieSpinner = new JSpinner(new SpinnerNumberModel(2000, 1000, 5000, 100));
        JLabel calorieLimitLabel = new JLabel("Calorie Limit:");
        calorieSpinner.setMaximumSize(calorieSpinner.getPreferredSize());
        JPanel caloriePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        caloriePanel.add(calorieLimitLabel);
        caloriePanel.add(calorieSpinner);
        this.add(caloriePanel);

        // Generate Button
        JButton generateButton = new JButton("Generate Meal Plan");
        generateButton.addActionListener(this::handleGenerateButtonAction);
        this.add(generateButton);

        mealPlanDisplayPanel = new JPanel(); // Class level field
        mealPlanDisplayPanel.setLayout(new BoxLayout(mealPlanDisplayPanel, BoxLayout.Y_AXIS));
        this.add(new JScrollPane(mealPlanDisplayPanel));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Generate Meal Plan")) {
                handleGenerateButtonAction(e);
            }
        }
    }


    private void handleGenerateButtonAction(ActionEvent e){
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
        List<String> dietPreferences = dietCheckBoxes.stream()
                .filter(JCheckBox::isSelected)
                .map(JCheckBox::getText)
                .collect(Collectors.toList());

        // Get selected health preferences
        List<String> healthPreferences = healthCheckBoxes.stream()
                .filter(JCheckBox::isSelected)
                .map(JCheckBox::getText)
                .collect(Collectors.toList());

        // Combine preferences and create meal plan
        List<String> combinedPreferences = new ArrayList<>(dietPreferences);
        combinedPreferences.addAll(healthPreferences);
        int calorieLimit = (Integer) calorieSpinner.getValue();
        String preferences = String.join(", ", combinedPreferences);
        mealPlanController.createMealPlan(startDate, endDate, preferences, calorieLimit);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Display MealPlan")) {
            updateMealPlanDisplay(mealPlanViewModel.getState());
        }
    }

    private void updateMealPlanDisplay(MealPlanState state) {
        mealPlanDisplayPanel.removeAll();
        MealPlan mealPlan = state.getMealPlan();

        JFrame frame = new JFrame("Meal Plan");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for showing input criteria
        JPanel criteriaPanel = new JPanel(new GridLayout(0, 2));
        criteriaPanel.setBorder(BorderFactory.createTitledBorder("Meal Plan Criteria"));

        // Adding input criteria
        criteriaPanel.add(new JLabel("Meal Plan "));
        criteriaPanel.add(new JLabel(mealPlan.getIdentifier().toString()));
        criteriaPanel.add(new JLabel("Start Day:"));
        criteriaPanel.add(new JLabel(mealPlan.getStartDate().toString()));
        criteriaPanel.add(new JLabel("End Day:"));
        criteriaPanel.add(new JLabel(mealPlan.getEndDate().toString()));
        criteriaPanel.add(new JLabel("Diet:"));
        criteriaPanel.add(new JLabel(String.join(", ", mealPlan.getDiet())));
        criteriaPanel.add(new JLabel("Caloric Limit:"));
        criteriaPanel.add(new JLabel(String.valueOf(mealPlan.getCalorieLimit())));

        frame.add(criteriaPanel, BorderLayout.NORTH);

        // Panel for meal plans
        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new BoxLayout(daysPanel, BoxLayout.Y_AXIS));

        for (MealPlanDay mealPlanDay : mealPlan.getMealPlanDays()) {
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

        mealPlanDisplayPanel.revalidate();
        mealPlanDisplayPanel.repaint();
    }
}
