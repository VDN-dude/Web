package by.tms.factory;

import by.tms.entity.Operation;
import by.tms.service.CalculatorOperation;
import by.tms.service.MulOperation;

public class MulOp extends OperationFactory{
    @Override
    public CalculatorOperation createOperation(Operation operation) {
        return new MulOperation(operation);
    }
}
