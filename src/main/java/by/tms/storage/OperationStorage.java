package by.tms.storage;

import by.tms.entity.Operation;

import java.util.List;

public interface OperationStorage {
    void save(Operation operation);
    List<Operation> findByUserId(int userId);
}
