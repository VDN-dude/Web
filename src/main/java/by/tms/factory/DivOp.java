package by.tms.factory;

import by.tms.entity.Operation;
import by.tms.service.CalculatorOperation;
import by.tms.service.DivOperation;

public class DivOp extends OperationFactory{
    @Override
    public CalculatorOperation createOperation(Operation operation) {
        return new DivOperation(operation);
    }
}
