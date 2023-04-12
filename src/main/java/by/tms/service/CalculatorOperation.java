package by.tms.service;

import by.tms.entity.Operation;

public interface CalculatorOperation {
    void process();
    Operation getFinalResult();
}
