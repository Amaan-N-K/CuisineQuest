package use_case.dashboard;

public class DashboardInputData {
    private final String buttonName;

    public DashboardInputData(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
