package by.tms.factory;

import by.tms.entity.Operation;
import by.tms.service.CalculatorOperation;
import by.tms.storage.JDBCOperationStorage;
import by.tms.storage.OperationStorage;

public abstract class OperationFactory {
    private final OperationStorage storage = JDBCOperationStorage.getInstance();

    public Operation executeOperation(Operation operation) {
        CalculatorOperation calculatorOperation = createOperation(operation);
        calculatorOperation.process();
        Operation finalResult = calculatorOperation.getFinalResult();
        if (finalResult.getUserId() != 0) {
            Thread thread = new Thread(() -> storage.save(finalResult));
            thread.start();
        }
        return operation;
    }

    public abstract CalculatorOperation createOperation(Operation operation);
}
