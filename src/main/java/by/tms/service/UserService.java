package by.tms.service;

import by.tms.entity.User;
import by.tms.storage.JDBCUserStorage;
import by.tms.storage.UserStorage;

public class UserService extends JDBCUserStorage{
    private final UserStorage storage = new JDBCUserStorage();

    public void save(User user){
        storage.save(user);
    }
    public java.util.Optional<User> findByEmail(String email){
        return storage.findByEmail(email);
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
