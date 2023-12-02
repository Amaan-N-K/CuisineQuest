package InterfaceAdapters.SignUp;

public class SignUpState {
    private String userId = "";
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String confirmPassword = "";
    private String confirmPasswordError = null;

    public SignUpState(SignUpState state){
        userId = state.userId;
        username = state.username;
        usernameError = state.usernameError;
        password = state.password;
        passwordError = state.passwordError;
        confirmPassword = state.confirmPassword;
        confirmPasswordError = state.confirmPasswordError;
    }
    SignUpState(){

    }

    public String getUserId() {
        return userId;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String validate) {
        usernameError = validate;
    }

    public void setUserId(String userid) {
        userId = userid;

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String message) {
        passwordError = message;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setConfirmPasswordError(String message) {
        confirmPasswordError = message;
    }
}
