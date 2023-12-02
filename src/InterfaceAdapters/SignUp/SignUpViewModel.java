package InterfaceAdapters.SignUp;

import InterfaceAdapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignUpViewModel extends ViewModel {

    public final String TITLE_LABEL = "Sign Up";
    public final String USERNAME_LABEL = "Username";
    public final String PASSWORD_LABEL = "Password";
    public final String CONFIRM_PASSWORD_LABEL = "Confirm Password";

    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String LOGIN_BUTTON_LABEL = "Log in";

    private SignUpState state = new SignUpState();

    public SignUpViewModel() {
        super("sign up");
    }

    public void setState(SignUpState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }

    public SignUpState getState() {
        return state;
    }

}
