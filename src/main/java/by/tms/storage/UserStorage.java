package by.tms.storage;

import by.tms.entity.User;

import java.util.Optional;

public interface UserStorage {
    void save(User user);
    Optional<User> findByEmail(String email);
}
