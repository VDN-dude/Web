package by.tms.storage;

import by.tms.entity.User;

import java.util.Optional;

public interface UserStorage {
    void save(User user);
    Optional<User> findByEmail(String email);
    boolean checkEmail(String email);
    boolean checkUsername(String username);
}
