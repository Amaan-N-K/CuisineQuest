package Entities;

public class UserFactory implements UserFactoryInterface{
    @Override
    public User create(String userid) {
        return new User(userid);
    }
}
