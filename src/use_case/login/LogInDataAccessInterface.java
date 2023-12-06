package use_case.login;

import entities.User;

public interface LogInDataAccessInterface {
    void setActive(User user);

    User getByUsername(String username);

    boolean existByUsername(String username);
}
