package UseCase.ViewFavorites;

import Entities.User;

public interface ViewFavoritesDataAccessInterface {
    String getActive();

    User getByID(String activeUserId);
}
