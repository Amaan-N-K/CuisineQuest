package use_case.save_favorite;

import entities.User;

public interface UserDataAccessInterface {
    boolean existsByID(String userId);

    User getByID(String userId);

    void save(User existingUser);

    String getActive();

}
