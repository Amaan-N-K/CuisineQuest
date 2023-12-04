package InterfaceAdapters.MealPlanCreation;

import Entities.MealPlan;
import Entities.MealPlanDay;
import UseCase.recipesearch.RecipeSearchDTO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MealPlanState {
    private MealPlan mealPlan;
    private String errorMessage;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    // Private setters

    private void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    // Update the state with a new meal plan
    public void updateMealPlan(MealPlan newMealPlan) {
        this.mealPlan = newMealPlan;
        firePropertyChanged("Display MealPlan", null, null);}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    // Notify observers of property changes
    protected void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, null, null);
    }

    // Getters
    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}