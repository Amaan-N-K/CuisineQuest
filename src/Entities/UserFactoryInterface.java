package Entities;

import java.time.LocalDateTime;

public interface UserFactoryInterface {
     User create(String userid, String username, String password, LocalDateTime creationTime);

}
