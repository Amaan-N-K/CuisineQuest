package InterfaceAdapters.grocery_list;

import UseCase.grocery_list.GroceryListInteractor;
import InterfaceAdapters.grocery_list.GroceryListViewModel;

public class GroceryListController {

    private final GroceryListInteractor interactor;
    private final GroceryListView view;
    private final GroceryListPresenter presenter;

    public GroceryListController(GroceryListInteractor interactor, GroceryListView view, GroceryListViewModel viewModel) {
        this.interactor = interactor;
        this.view = view;
        this.presenter = new GroceryListPresenter(view);

        this.view.addGenerateButtonListener(new GenerateButtonListener());

        viewModel.addPropertyChangeListener(presenter);
    }


    private class GenerateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MealPlan mealPlan = view.getMealPlan();
            interactor.generateGroceryList(mealPlan);
        }
    }

}