package view;

import interface_adapter.dashboard.DashboardController;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import javax.swing.*;
import java.awt.*;

class DashboardViewTest {

    @Mock
    private DashboardController mockController;

    private DashboardView dashboardView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dashboardView = new DashboardView(mockController);
    }

    @Test
    void testButtonClicks() {
        for (Component comp : dashboardView.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                String buttonText = button.getText();

                button.doClick();

                verify(mockController).execute(buttonText);
            }
        }
    }

}
