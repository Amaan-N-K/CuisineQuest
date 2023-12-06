package interface_adapter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;

class ViewManagerModelTest {

    private ViewManagerModel viewManagerModel;

    @Mock
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        viewManagerModel = new ViewManagerModel();
        viewManagerModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetActiveViewAndFirePropertyChanged() {
        String newActiveView = "newView";
        viewManagerModel.setActiveView(newActiveView);
        viewManagerModel.firePropertyChanged();

        verify(mockListener).propertyChange(any());
        assertEquals(newActiveView, viewManagerModel.getActiveView(), "The active view should be updated.");
    }

    @Test
    void testAddPropertyChangeListener() {
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewManagerModel.addPropertyChangeListener(newListener);

        viewManagerModel.firePropertyChanged();

        verify(mockListener).propertyChange(any());
        verify(newListener).propertyChange(any());
    }


}
