package InterfaceAdapters.MealPlanCreation;

import Entities.MealPlan;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MealPlanViewModel {
    private MealPlanState state = new MealPlanState();
    public final String viewName = "meal plan";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setState(MealPlanState state) {
        this.state = state;
        firePropertyChanged();
    }
    public MealPlanState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void updateMealPlanState(MealPlan mealPlan){
        state.updateMealPlan(mealPlan);
        firePropertyChanged();
    }
}