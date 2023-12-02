package InterfaceAdapters.LogIn;

import InterfaceAdapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogInViewModel extends ViewModel {
    public final String TITLE_LABEL = "Log In View";
    public final String USERNAME_LABEL = "Enter username";
    public final String PASSWORD_LABEL = "Enter password";

    public final String LOGIN_BUTTON_LABEL = "Log in";
    public final String SIGNUP_BUTTON_LABEL = "Sign up";

    private LogInState state = new LogInState();

    public LogInViewModel() {
        super("log in");
    }

    public void setState(LogInState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LogInState getState() {
        return state;
    }
}
