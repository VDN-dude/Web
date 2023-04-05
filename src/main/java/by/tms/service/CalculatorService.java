package by.tms.service;

import by.tms.entity.Operation;
import by.tms.storage.JDBCOperationStorage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CalculatorService {
   private final JDBCOperationStorage storage = new JDBCOperationStorage();
   public Optional<Operation> calculate(Operation operation){
      switch (operation.getType()){
         case SUM:
            operation.setTime(LocalDateTime.now());
            Optional<Operation> sumRes = Optional.of(operation.setResult(operation.getNum1() + operation.getNum2()));
            if (!operation.getUsername().equals("guest")) {
               Thread sumThread = new Thread(() -> storage.save(sumRes.get()));
               sumThread.start();
            }
            return sumRes;
         case SUB:
            operation.setTime(LocalDateTime.now());
            Optional<Operation> subRes = Optional.of(operation.setResult(operation.getNum1() - operation.getNum2()));
            if (!operation.getUsername().equals("guest")) {
               Thread subThread = new Thread(() -> storage.save(subRes.get()));
               subThread.start();
            }
            return subRes;
         case MUL:
            operation.setTime(LocalDateTime.now());
            Optional<Operation> mulRes = Optional.of(operation.setResult(operation.getNum1() * operation.getNum2()));
            if (!operation.getUsername().equals("guest")) {
               Thread mulThread = new Thread(() -> storage.save(mulRes.get()));
               mulThread.start();
            }
            return mulRes;
         case DIV:
            operation.setTime(LocalDateTime.now());
            Optional<Operation> divRes = Optional.of(operation.setResult(operation.getNum1() / operation.getNum2()));
            if (!operation.getUsername().equals("guest")) {
               Thread divThread = new Thread(() -> storage.save(divRes.get()));
               divThread.start();
            }
            return divRes;
      }
      return Optional.empty();
   }

   public List<Operation> findUserOperations(String username){
      return storage.findUserOperations(username);
   }

}
