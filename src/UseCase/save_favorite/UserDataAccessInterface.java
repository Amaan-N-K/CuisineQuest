package UseCase.save_favorite;

import Entities.User;

public interface UserDataAccessInterface {
    boolean existsByID(String userId);

    User GetByID(String userId);

    void updateUser(User existingUser);
}
