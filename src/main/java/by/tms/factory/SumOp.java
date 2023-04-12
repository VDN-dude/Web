package by.tms.factory;

import by.tms.entity.Operation;
import by.tms.service.CalculatorOperation;
import by.tms.service.SumOperation;

public class SumOp extends OperationFactory{
    @Override
    public CalculatorOperation createOperation(Operation operation) {
        return new SumOperation(operation);
    }
}
