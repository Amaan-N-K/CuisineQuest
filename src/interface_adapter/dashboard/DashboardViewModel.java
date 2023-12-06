package interface_adapter.dashboard;

public class DashboardViewModel {
    public final String viewName = "dashboard";

    // The DashboardViewModel does not need to change the state of DashboardView, it exists to allow other presenters
    // to switch from their view to Dashboard View
}
