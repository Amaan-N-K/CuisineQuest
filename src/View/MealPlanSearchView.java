package View;

import InterfaceAdapter.MealPlanCreation.MealPlanController;
import InterfaceAdapter.MealPlanCreation.MealPlanViewModel;
import InterfaceAdapter.MealPlanCreation.MealPlanState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


public class MealPlanSearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String ViewName = "Meal Plan Creation";
    private MealPlanViewModel mealPlanViewModel;
    private MealPlanController mealPlanController;

    private JFrame frame;
    private JTextField startDateField, endDateField;
    private JList<String> dietTypeList, healthList;
    private JSpinner calorieSpinner;
    public MealPlanSearchView(MealPlanController mealPlanController, MealPlanViewModel mealPlanViewModel) {

        this.mealPlanController = mealPlanController;
        this.mealPlanViewModel = mealPlanViewModel;
        mealPlanViewModel.addPropertyChangeListener(new PropertyChangeListener(){

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateViewOnStateChange();
            }
        });

        createAndShowGUI();
    }

    private void updateViewOnStateChange() {
        MealPlanState state = mealPlanViewModel.getState();
        if (state.isCreationSuccess()) {
            MealPlanDisplayView mealPlanView = new MealPlanDisplayView(state.getMealPlan());
            mealPlanView.displayMealPlan();
            frame.dispose(); // Optionally close the search view
        } else {
            JOptionPane.showMessageDialog(frame, state.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    public void createAndShowGUI() {
        JFrame frame = new JFrame("Meal Plan Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

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
        generateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(generateButton);

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
    public void actionPerformed(ActionEvent e) {
        // Call handleGenerateButtonAction when generateButton is clicked
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Generate Meal Plan")) {
                handleGenerateButtonAction();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property change events
        if (evt.getPropertyName().equals("state")) {
            updateViewOnStateChange();
        }
    }
}
