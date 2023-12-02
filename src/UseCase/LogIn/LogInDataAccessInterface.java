package UseCase.LogIn;

import Entities.User;

import java.util.Optional;

public interface LogInDataAccessInterface {
    void setActive(User user);

    User getByUsername(String username);

    boolean existByUsername(String username);
}
