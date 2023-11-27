package UseCase.SignUp;

import Entities.User;

public interface SignUpDataAccessInterface {
    void save(User user);

    boolean existByUsername(String username);
}
