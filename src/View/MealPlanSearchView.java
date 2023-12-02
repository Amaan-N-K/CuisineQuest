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


public class MealPlanSearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private MealPlanViewModel mealPlanViewModel;
    private MealPlanController mealPlanController;

    // UI Components
    private JTextField startDateField, endDateField;
    private JList<String> dietTypeList, healthList;
    private JSpinner calorieSpinner;
    private JPanel mealPlanDisplayPanel;
    private JFrame frame;


    public MealPlanSearchView(MealPlanController mealPlanController, MealPlanViewModel mealPlanViewModel) {

        this.mealPlanController = mealPlanController;
        this.mealPlanViewModel = mealPlanViewModel;
        mealPlanViewModel.addPropertyChangeListener(this);
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Create Your Meal Plan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Date range selection
        JLabel startDateLabel = new JLabel("Start Date:");
        JTextField startDateField = new JTextField(10);
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDatePanel.add(startDateLabel);
        startDatePanel.add(startDateField);
        panel.add(startDatePanel);

        JLabel endDateLabel = new JLabel("End Date:");
        JTextField endDateField = new JTextField(10);
        JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        endDatePanel.add(endDateLabel);
        endDatePanel.add(endDateField);
        panel.add(endDatePanel);

        // Diet Types
        JLabel dietTypeLabel = new JLabel("Diet Type:");
        String[] dietTypes = { "Balanced", "High-Fiber", "High-Protein", "Low-Carb", "Low-Fat", "Low-Sodium" };
        JList<String> dietTypeList = new JList<>(dietTypes);
        dietTypeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane dietTypeScrollPane = new JScrollPane(dietTypeList);
        dietTypeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dietTypeScrollPane.setPreferredSize(new Dimension(200, 100));

        // Health Labels
        JLabel healthLabel = new JLabel("Health Labels:");
        String[] healthOptions = {"Alcohol-free", "Immuno-Supportive", "Celery-free", "Crustacean-free", "Dairy-free",
                "Egg-free", "Fish-free", "FODMAP-free", "Gluten-free", "Keto-friendly", "Kidney-friendly", "Kosher",
                "Low-potassium", "Lupine-free", "Mustard-free", "Low-fat-abs", "No-oil-added", "Low-sugar", "Paleo",
                "Peanut-free", "Pescatarian", "Pork-free", "Red-meat-free", "Sesame-free", "Shellfish-free", "Soy-free",
                "Sugar-conscious", "Tree-nut-free", "Vegan", "Vegetarian", "Wheat-free"};
        JList<String> healthList = new JList<>(healthOptions);
        healthList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane healthScrollPane = new JScrollPane(healthList);
        healthScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        healthScrollPane.setPreferredSize(new Dimension(200, 100));

        // Main panel configuration
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(dietTypeLabel);
        mainPanel.add(dietTypeScrollPane);
        mainPanel.add(healthLabel);
        mainPanel.add(healthScrollPane);

        // Adding main panel to the frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);


        // Calorie limit
        JLabel calorieLimitLabel = new JLabel("Calorie Limit:");
        JSpinner calorieSpinner = new JSpinner(new SpinnerNumberModel(2000, 1000, 5000, 100));
        calorieSpinner.setMaximumSize(calorieSpinner.getPreferredSize());
        JPanel caloriePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        caloriePanel.add(calorieLimitLabel);
        caloriePanel.add(calorieSpinner);
        panel.add(caloriePanel);


        // Generate Button
        JButton generateButton = new JButton("Generate Meal Plan");
        generateButton.addActionListener(this);
        this.add(generateButton);

        mealPlanDisplayPanel = new JPanel();
        mealPlanDisplayPanel.setLayout(new BoxLayout(mealPlanDisplayPanel, BoxLayout.Y_AXIS));
        this.add(new JScrollPane(mealPlanDisplayPanel));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Generate Meal Plan")) {
                handleGenerateButtonAction();
            }
        }
    }


        private void handleGenerateButtonAction(){
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            List<String> dietPreferences = dietTypeList.getSelectedValuesList();
            List<String> healthPreferences = healthList.getSelectedValuesList();
            List<String> combinedPreferences = new ArrayList<>(dietPreferences);
            combinedPreferences.addAll(healthPreferences);
            int calorieLimit = (Integer) calorieSpinner.getValue();
            mealPlanController.createMealPlan(startDate, endDate, combinedPreferences.toString(), calorieLimit);
        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
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
