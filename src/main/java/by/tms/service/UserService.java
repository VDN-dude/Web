package by.tms.service;

import by.tms.entity.User;
import by.tms.storage.JDBCUserStorage;
import by.tms.storage.UserStorage;

import java.util.Optional;

public class UserService extends JDBCUserStorage{

    @Override
    public void save(User user) {
        super.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return super.findByEmail(email);
    }

    @Override
    public boolean checkEmail(String email) {
        return super.checkEmail(email);
    }

    @Override
    public boolean checkUsername(String username) {
        return super.checkUsername(username);
    }
}
