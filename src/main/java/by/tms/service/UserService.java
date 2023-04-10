package by.tms.service;

import by.tms.entity.User;
import by.tms.storage.JDBCUserStorage;
import by.tms.storage.UserStorage;

import java.util.Optional;

public class UserService{
    private final UserStorage storage = new JDBCUserStorage();

    public void save(User user) {
        storage.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return storage.findByEmail(email);
    }

    public boolean checkEmail(String email) {
        return storage.checkEmail(email);
    }

    public boolean checkUsername(String username) {
        return storage.checkUsername(username);
    }
}
