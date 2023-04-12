package by.tms.service;


import by.tms.entity.Operation;
import by.tms.factory.*;
import by.tms.storage.JDBCOperationStorage;
import by.tms.storage.OperationStorage;

import java.util.List;
import java.util.Optional;

public class CalculatorService {
    private static CalculatorService instance;
    private final OperationStorage storage = JDBCOperationStorage.getInstance();

    private CalculatorService() {

    }

    public static CalculatorService getInstance() {
        if (instance == null) {
            instance = new CalculatorService();
        }
        return instance;
    }

    public Optional<Operation> calculate(CalculatorOperation operation) {
        operation.process();
        storage.save(operation.getFinalResult());
        return Optional.ofNullable(operation.getFinalResult());
    }

    public List<Operation> findByUserId(int userId) {
        return storage.findByUserId(userId);
    }

}
