package use_case.signup;

import entities.User;

public interface SignUpDataAccessInterface {
    void save(User user);

    boolean existByUsername(String username);

    void setActive(User user);
}
