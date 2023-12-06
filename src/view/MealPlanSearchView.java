package view;

import interface_adapter.meal_plan_creation.MealPlanController;
import interface_adapter.meal_plan_creation.MealPlanViewModel;
import interface_adapter.meal_plan_creation.MealPlanState;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URISyntaxException;
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

    private JPanel mealPlanInfoPanel;

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

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        this.add(backButton);

        // Date range selection
        startDateField = new JTextField(10);
        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDatePanel.add(startDateLabel);
        startDatePanel.add(startDateField);
        this.add(startDatePanel);

        endDateField = new JTextField(10);
        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
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

        mealPlanInfoPanel = new JPanel();
        mealPlanInfoPanel.setLayout(new BoxLayout(mealPlanInfoPanel, BoxLayout.Y_AXIS));
        JScrollPane mealPlanInfoScrollPane = new JScrollPane(mealPlanInfoPanel); // Wrap in a scroll pane
        this.add(mealPlanInfoScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            if (button.getText().equals("Generate Meal Plan")) {
                handleGenerateButtonAction(e);
            } else if (button.getText().equals("Back")) {
                mealPlanController.back();
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
        }else if ("Invalid dates.".equals(evt.getPropertyName())) {
            // Display error message
            String errorMessage = mealPlanViewModel.getState().getErrorMessage();
            if (errorMessage != null && !errorMessage.isEmpty()) {
                JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateMealPlanDisplay(MealPlanState state) {
        // Clearing the existing content of the mealPlanInfoPanel
        mealPlanInfoPanel.removeAll();

        // Panel for displaying meal plans for each day
        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new BoxLayout(daysPanel, BoxLayout.Y_AXIS));

        int totalDays = state.getBreakfastNames().size(); // Assuming all lists have the same size
        for (int dayIndex = 0; dayIndex < totalDays; dayIndex++) {
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(BorderFactory.createTitledBorder("Day " + (dayIndex + 1)));

            // Add meal details for each day
            addMealToPanel(dayPanel, "Breakfast", state.getBreakfastNames().get(dayIndex), state.getBreakfastDescriptions().get(dayIndex));
            addMealToPanel(dayPanel, "Lunch", state.getLunchNames().get(dayIndex), state.getLunchDescriptions().get(dayIndex));
            addMealToPanel(dayPanel, "Dinner", state.getDinnerNames().get(dayIndex), state.getDinnerDescriptions().get(dayIndex));

            daysPanel.add(dayPanel);
        }

        // Adding daysPanel to mealPlanInfoPanel
        mealPlanInfoPanel.add(new JScrollPane(daysPanel)); // Wrap daysPanel in a JScrollPane

        // Refresh the meal plan info panel to display new content
        mealPlanInfoPanel.revalidate();
        mealPlanInfoPanel.repaint();
    }

    private void addMealToPanel(JPanel panel, String mealType, String name, String description) {
        JLabel nameLabel = new JLabel(mealType + ": " + name);
        JEditorPane descriptionPane = new JEditorPane("text/html", "<html><a href='" + description + "'>" + description + "</a></html>");
        descriptionPane.setEditable(false);
        descriptionPane.setOpaque(false);
        descriptionPane.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(nameLabel);
        panel.add(descriptionPane);
    }
}
