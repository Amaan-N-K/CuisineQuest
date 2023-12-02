package InterfaceAdapters.LogIn;

public class LogInState {
    private String userId = "";
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;

    public LogInState(LogInState copy) {
        userId = copy.userId;
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LogInState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
