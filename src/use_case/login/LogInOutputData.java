package use_case.login;

public class LogInOutputData {
    private final String username;
    private  final String userId;
    public LogInOutputData(String userId, String username) {
        this.userId = userId;
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }
}
