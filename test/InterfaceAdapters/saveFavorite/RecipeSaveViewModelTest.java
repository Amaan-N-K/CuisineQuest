package InterfaceAdapters.saveFavorite;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;

class RecipeSaveViewModelTest {

    private RecipeSaveViewModel viewModel;

    @Mock
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new RecipeSaveViewModel();
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetStateAndFirePropertyChanged() {
        RecipeSaveState newState = new RecipeSaveState();
        viewModel.setState(newState);
        viewModel.firePropertyChanged();

        verify(mockListener).propertyChange(any());
        assertEquals(newState, viewModel.getState(), "The state should be updated.");
    }

    @Test
    void testAddPropertyChangeListener() {
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(newListener);

        viewModel.firePropertyChanged();

        verify(mockListener).propertyChange(any());
        verify(newListener).propertyChange(any());
    }
}

