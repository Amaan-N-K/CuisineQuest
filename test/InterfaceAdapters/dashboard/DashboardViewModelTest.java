package InterfaceAdapters.dashboard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardViewModelTest {

    @Test
    public void testViewName() {
        DashboardViewModel viewModel = new DashboardViewModel();
        assertEquals("dashboard", viewModel.viewName, "The view name should be 'dashboard'.");
    }
}