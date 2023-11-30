package UseCase.save_favorite;

import Entities.User;

public interface UserDataAccessInterface {
    boolean existsByID(String userId);

    User getByID(String userId);

    void save(User existingUser);
}
