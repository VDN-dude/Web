package by.tms.service;


import by.tms.entity.Operation;
import by.tms.storage.JDBCOperationStorage;
import by.tms.storage.OperationStorage;

import java.util.List;

public class CalculatorService {
   private static CalculatorService instance;
   private final OperationStorage storage = JDBCOperationStorage.getInstance();
   private CalculatorService(){

   }
   public static CalculatorService getInstance(){
      if (instance == null){
         instance = new CalculatorService();
      }
      return instance;
   }
   public double calculate(CalculatorOperation operation){
      operation.process();
      return operation.getFinalResult();
   }
   public List<Operation> findByUserId(int userId) {
      return storage.findByUserId(userId);
   }

}
