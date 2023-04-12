package by.tms.factory;

import by.tms.entity.Operation;
import by.tms.service.CalculatorOperation;
import by.tms.service.SubOperation;

public class SubOp extends OperationFactory{
    @Override
    public CalculatorOperation createOperation(Operation operation) {
        return new SubOperation(operation);
    }
}
