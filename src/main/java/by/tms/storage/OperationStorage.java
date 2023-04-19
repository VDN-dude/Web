package by.tms.storage;

import by.tms.entity.Operation;
import by.tms.entity.User;

import java.util.List;

public interface OperationStorage {
    void save(Operation operation);
    List<Operation> findByUser(User user);
}
