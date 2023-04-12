package by.tms.factory;

import by.tms.entity.Operation;
import by.tms.service.*;

import java.util.Optional;

public abstract class OperationFactory {

    public static Optional<CalculatorOperation> createOperation(Operation operation) {
        switch (operation.getType()) {
            case SUM:
                return Optional.of(new SumOperation(operation));
            case SUB:
                return Optional.of(new SubOperation(operation));
            case MUL:
                return Optional.of(new MulOperation(operation));
            case DIV:
                return Optional.of(new DivOperation(operation));
        }
        return Optional.empty();
    }
}
