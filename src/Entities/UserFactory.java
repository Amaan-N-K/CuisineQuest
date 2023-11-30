package Entities;

import java.time.LocalDateTime;

public class UserFactory implements UserFactoryInterface{
    @Override
    public User create(String userid, String username, String password, LocalDateTime creationTime) {
        return new User(userid, username, password, creationTime);
    }
}
