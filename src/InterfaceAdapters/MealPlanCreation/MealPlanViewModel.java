package InterfaceAdapters.MealPlanCreation;


import Entities.MealPlan;
import InterfaceAdapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MealPlanViewModel extends ViewModel {
    private MealPlanState state = new MealPlanState();

    public MealPlanViewModel() {
        super("sign up");
    }

    public void setState(MealPlanState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MealPlanState getState() {
        return state;
    }

    public void updateMealPlanState(MealPlan mealPlan){
        state.setMealPlan(mealPlan);
        state.setCreationSuccess(true);
        firePropertyChanged();
    }
}