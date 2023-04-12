package by.tms.service;


import by.tms.entity.Operation;
import by.tms.factory.*;
import by.tms.storage.JDBCOperationStorage;
import by.tms.storage.OperationStorage;

import java.util.List;
import java.util.Optional;

public class CalculatorService {
    private Operation operation;
    private static OperationFactory factory;
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

    public void configure(Operation operation) {
        switch (operation.getType()) {
            case SUM:
                factory = new SumOp();
                this.operation = operation;
                break;
            case SUB:
                factory = new SubOp();
                this.operation = operation;
                break;
            case MUL:
                factory = new MulOp();
                this.operation = operation;
                break;
            case DIV:
                factory = new DivOp();
                this.operation = operation;
                break;
        }

    }
    public Optional<Operation> calculate() {
        if (factory != null) {
            factory.executeOperation(operation);
            return Optional.ofNullable(operation);
        }
        return Optional.empty();
    }

    public List<Operation> findByUserId(int userId) {
        return storage.findByUserId(userId);
    }

}
