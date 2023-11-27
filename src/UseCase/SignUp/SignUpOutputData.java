package UseCase.SignUp;

import java.time.LocalDateTime;

public class SignUpOutputData {
    final private String userId;
    final private String username;
    final private LocalDateTime signupTime;

    public SignUpOutputData(String userId, String username, LocalDateTime currentTime) {
        this.userId = userId;
        this.username = username;
        this.signupTime = currentTime;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getSignupTime() {
        return signupTime;
    }
}
