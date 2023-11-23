package InterfaceAdapters.grocery_list;

import UseCase.grocery_list.GroceryListOutputBoundary;
import View.GroceryView;

import java.util.List;

public class GroceryListPresenter implements PropertyChangeListener, GroceryListOutputBoundary {

    private final GroceryView view;

    public GroceryListPresenter(GroceryView view) {
        this.view = view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if ("mealPlanInfo".equals(evt.getPropertyName())) {
            view.setMealPlanInfo((String) evt.getNewValue());
        } else if ("groceryList".equals(evt.getPropertyName())) {
            view.setGroceryList((String) evt.getNewValue());
        }
    }

    @Override
    public void presentGroceryList(List<String> groceryItems) {
        String groceryList = buildGroceryListString(groceryItems);
        view.setGroceryList(groceryList);
    }

    private String buildGroceryListString(List<String> groceryItems) {
        StringBuilder builder = new StringBuilder();
        for (String item : groceryItems) {
            builder.append(item).append("\n");
        }
        return builder.toString();
    }
}