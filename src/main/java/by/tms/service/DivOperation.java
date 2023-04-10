package by.tms.service;

import by.tms.entity.Operation;
import by.tms.entity.OperationType;
import by.tms.storage.JDBCOperationStorage;
import by.tms.storage.OperationStorage;

import java.time.LocalDateTime;

public class DivOperation implements CalculatorOperation {
    private final OperationStorage storage = new JDBCOperationStorage();
    private final Operation operation;

    public DivOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void process() {
        operation.setResult(operation.getNum1() / operation.getNum2());
        operation.setType(OperationType.DIV);
        operation.setTime(LocalDateTime.now());
        if (operation.getUserId()!= 0){
            Thread thread = new Thread(() -> storage.save(operation));
            thread.start();
        }
    }

    @Override
    public double getFinalResult() {
        return operation.getResult();
    }
}
