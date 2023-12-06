package interface_adapter.meal_plan_creation;

import use_case.meal_plan_creation.MealPlanOutputData;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class MealPlanState {
    private List<String> breakfastNames;
    private List<String> breakfastDescriptions;
    private List<String> lunchNames;
    private List<String> lunchDescriptions;
    private List<String> dinnerNames;
    private List<String> dinnerDescriptions;
    private String errorMessage;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Update the state with new meal plan output data
    public void updateMealPlan(MealPlanOutputData newMealPlanOutputData) {
        this.breakfastNames = newMealPlanOutputData.getBreakfastNames();
        this.breakfastDescriptions = newMealPlanOutputData.getBreakfastDescriptions();
        this.lunchNames = newMealPlanOutputData.getLunchNames();
        this.lunchDescriptions = newMealPlanOutputData.getLunchDescriptions();
        this.dinnerNames = newMealPlanOutputData.getDinnerNames();
        this.dinnerDescriptions = newMealPlanOutputData.getDinnerDescriptions();
        firePropertyChanged("Display MealPlan", null, null);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    // Notify observers of property changes
    protected void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    // Getters
    public List<String> getBreakfastNames() {
        return breakfastNames;
    }

    public List<String> getBreakfastDescriptions() {
        return breakfastDescriptions;
    }

    public List<String> getLunchNames() {
        return lunchNames;
    }

    public List<String> getLunchDescriptions() {
        return lunchDescriptions;
    }

    public List<String> getDinnerNames() {
        return dinnerNames;
    }

    public List<String> getDinnerDescriptions() {
        return dinnerDescriptions;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
