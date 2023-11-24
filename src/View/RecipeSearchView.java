package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeSearchView {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecipeSearchView().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Recipe Search Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Recipe Search");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Calories
        JLabel caloriesMinLabel = new JLabel("Calories Goal:");
        JTextField caloriesMinField = new JTextField(10);  // Set columns to 10 or adjust as necessary
        JPanel caloriesMinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        caloriesMinPanel.add(caloriesMinLabel);
        caloriesMinPanel.add(caloriesMinField);
        panel.add(caloriesMinPanel);

        // Ingredients
        JLabel ingredientsLabel = new JLabel("Ingredients:");

        JTextArea ingredientsArea = new JTextArea(3, 30); // Reduced the rows to 3 for smaller size
        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsArea);
        panel.add(ingredientsLabel);
        panel.add(ingredientsScrollPane);

        // Dietary Restrictions
        JLabel restrictionsLabel = new JLabel("Dietary Restrictions:");
        JPanel checkBoxPanel = new JPanel();
        JCheckBox halalCheckBox = new JCheckBox("Halal");
        JCheckBox glutenFreeCheckBox = new JCheckBox("Gluten Free");
        JCheckBox vegetarianCheckBox = new JCheckBox("Vegetarian");
        JCheckBox veganCheckBox = new JCheckBox("Vegan");
        JCheckBox kosherCheckBox = new JCheckBox("Kosher");
        checkBoxPanel.add(halalCheckBox);
        checkBoxPanel.add(glutenFreeCheckBox);
        checkBoxPanel.add(vegetarianCheckBox);
        checkBoxPanel.add(veganCheckBox);
        checkBoxPanel.add(kosherCheckBox);
        panel.add(restrictionsLabel);
        panel.add(checkBoxPanel);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Calorie Goal: " + caloriesMinField.getText());
                System.out.println("Ingredients: " + ingredientsArea.getText());
                System.out.println("Halal: " + halalCheckBox.isSelected());
                System.out.println("Gluten Free: " + glutenFreeCheckBox.isSelected());
            }
        });
        panel.add(searchButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
