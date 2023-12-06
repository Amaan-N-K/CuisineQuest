package use_case.view_favorites;

import entities.User;

public interface ViewFavoritesDataAccessInterface {
    String getActive();

    User getByID(String activeUserId);
}
