package interface_adapter.meal_plan_creation;

import use_case.meal_plan_creation.MealPlanOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MealPlanViewModel {

    public final String viewName = "meal plan";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private MealPlanState state = new MealPlanState();

    public void setState(MealPlanState state) {
        this.state = state;
    }
    public MealPlanState getState() {
        return state;
    }

    public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void updateMealPlanState(MealPlanOutputData mealPlanOutputData){
        state.updateMealPlan(mealPlanOutputData);
        firePropertyChanged("Display MealPlan", null, null);
    }
}