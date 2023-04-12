package by.tms.service;

import by.tms.entity.Operation;
import by.tms.entity.OperationType;

import java.time.LocalDateTime;

public class DivOperation implements CalculatorOperation {
    private final Operation operation;

    public DivOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void process() {
        operation.setResult(operation.getNum1() / operation.getNum2());
        operation.setType(OperationType.DIV);
        operation.setTime(LocalDateTime.now());
    }

    @Override
    public Operation getFinalResult() {
        return operation;
    }
}
