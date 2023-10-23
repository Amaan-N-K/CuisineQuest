package Entities;
public class CommonUserFactory {
    public User create(String name, String password) {
        return new CommonUser(name, password);
    }
}
