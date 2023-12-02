package InterfaceAdapters.dashboard;

import UseCase.dashboard.DashboardInputData;
import UseCase.dashboard.DashboardInputBoundary;

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
