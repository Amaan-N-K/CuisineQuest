package InterfaceAdapters.MealPlanCreation;

import UseCase.MealPlanCreation.MealPlanOutputData;

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

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, null);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void updateMealPlanState(MealPlanOutputData mealPlanOutputData){
        state.updateMealPlan(mealPlanOutputData);
        firePropertyChanged("Display MealPlan");
    }
}