package interface_adapter.dashboard;

import use_case.dashboard.DashboardInputData;
import use_case.dashboard.DashboardInputBoundary;

public class DashboardController {
    private DashboardInputBoundary inputBoundary;

    public DashboardController(DashboardInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void execute(String buttonName) {
        DashboardInputData inputData = new DashboardInputData(buttonName);
        inputBoundary.execute(inputData);
    }
}
