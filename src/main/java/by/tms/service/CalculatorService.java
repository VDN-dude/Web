package by.tms.service;


import by.tms.entity.Operation;
import by.tms.storage.JDBCOperationStorage;
import by.tms.storage.OperationStorage;

import java.util.List;

public class CalculatorService extends JDBCOperationStorage{
   public double calculate(CalculatorOperation operation){
      operation.process();
      return operation.getFinalResult();
   }
   @Override
   public List<Operation> findByUserId(int userId) {
      return super.findByUserId(userId);
   }

}
