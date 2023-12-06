package use_case.signup;

public class SignUpInputData {
    final private String username;
    final private String password;
    final private String confirmedPassword;
    public SignUpInputData(String username, String password, String confirmedPassword){
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }
}
