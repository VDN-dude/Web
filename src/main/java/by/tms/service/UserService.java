package by.tms.service;

import by.tms.entity.User;
import by.tms.storage.JDBCUserStorage;

public class UserService {
    private final JDBCUserStorage storage = new JDBCUserStorage();

    public void save(User user){
        storage.save(user);
    }
    public java.util.Optional<User> findByEmail(String email){
        return storage.findByEmail(email);
    }
}
