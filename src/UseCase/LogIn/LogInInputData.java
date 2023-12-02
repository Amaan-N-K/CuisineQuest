package UseCase.LogIn;

public class LogInInputData {
    final private String username;
    final private String password;

    public LogInInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
