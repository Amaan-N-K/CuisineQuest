package use_case.meal_plan_creation;

import java.util.List;

public class MealPlanOutputData {

    private List<String> breakfastNames;
    private List<String> breakfastDescriptions;
    private List<String> lunchNames;
    private List<String> lunchDescriptions;
    private List<String> dinnerNames;
    private List<String> dinnerDescriptions;

    public MealPlanOutputData(
            List<String> breakfastNames,
            List<String> breakfastDescriptions,
            List<String> lunchNames,
            List<String> lunchDescriptions,
            List<String> dinnerNames,
            List<String> dinnerDescriptions
    ) {
        this.breakfastNames = breakfastNames;
        this.breakfastDescriptions = breakfastDescriptions;
        this.lunchNames = lunchNames;
        this.lunchDescriptions = lunchDescriptions;
        this.dinnerNames = dinnerNames;
        this.dinnerDescriptions = dinnerDescriptions;
    }

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
}
