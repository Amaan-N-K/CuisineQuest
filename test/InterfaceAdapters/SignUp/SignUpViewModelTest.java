package InterfaceAdapters.SignUp;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;

class SignUpViewModelTest {

    private SignUpViewModel viewModel;

    @Mock
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new SignUpViewModel();
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetStateAndFirePropertyChanged() {
        SignUpState newState = new SignUpState();
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
