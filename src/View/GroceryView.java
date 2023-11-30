package View;

import InterfaceAdapters.grocery_list.GroceryController;
import InterfaceAdapters.grocery.list.GroceryState;
import InterfaceAdapters.grocery_list.GroceryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GroceryListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final JButton groceryListButton;
    private final JTextArea groceryListTextArea;
    private final JLabel mealPlanInfoLabel;

    public GroceryListView(GroceryListController controller, GroceryListViewModel groceryListViewModel) {
        this.groceryListController = controller;
        this.groceryListViewModel = groceryListViewModel;
        this.groceryListViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Grocery List Generator");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        mealPlanInfoLabel = new JLabel("Meal Plan Information: ");
        mealPlanInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        groceryListTextArea = new JTextArea();
        groceryListTextArea.setEditable(false);

        groceryListButton = new JButton("Generate Grocery List");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(mealPlanInfoLabel);
        this.add(groceryListTextArea);
        this.add(groceryListButton);
    }

    public void actionPerformed(ActionEvent evt) {System.out.println("Click" + evt.getActionCommand());}

    public void propertyChange(PropertyChangeEvent evt) {
        GroceryState state = (GroceryState) evt.getNewValue();
        setFields(state);
    }

    // private void setFields(GroceryState state) { }

    public void setMealPlanInfo(String mealPlanInfo){
        mealPlanInfoLabel.setText("Meal Plan Information: " + mealPlanInfo);
    }

    public void setGroceryList(String groceryList) {
        groceryListTextArea.setText("Genereated Grocery List:\n" + groceryList);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grocery List View");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GroceryListView groceryListView = new GroceryListView();
            frame.getContentPane().add(groceryListView);

            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}
