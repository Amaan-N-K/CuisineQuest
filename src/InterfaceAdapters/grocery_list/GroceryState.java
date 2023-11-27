package InterfaceAdapters.grocery_list;

import java.util.List;

public class GroceryListState {

    private String mealPlanInfo = "";
    private List<String> groceryList = new ArrayList<>();

    public GroceryListState(GroceryListState copy) {
        mealPlanInfo = copy.mealPlanInfo;
        groceryList = copy.groceryList;
    }

    public GroceryListState() {}

    public String getMealPlanInfo() {
        return mealPlanInfo;
    }

    public void setMealPlanInfo(String mealPlanInfo) {
        this.mealPlanInfo = mealPlanInfo;
    }

    public String getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(String groceryList) {
        this.groceryList = groceryList;
    }
}
