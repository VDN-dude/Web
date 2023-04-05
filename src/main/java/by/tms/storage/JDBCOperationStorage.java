package by.tms.storage;

import by.tms.entity.Operation;
import by.tms.entity.OperationType;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDBCOperationStorage{
    private final Connection connection;
    private static final String POSTGRESQL_USER = "postgres";
    private static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String POSTGRESQL_PASSWORD = "0314";
    private static final String SELECT_USER_OPERATIONS = "select * from operation where username = ?";
    private static final String WRITE_OPERATION = "insert into operation(num1, type, num2, result, time, username) values (?, ?, ?, ?, ?, ?)";

    public JDBCOperationStorage() {
        try {
            this.connection = DriverManager.getConnection(POSTGRESQL_URL, POSTGRESQL_USER, POSTGRESQL_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Operation operation) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(WRITE_OPERATION);
            preparedStatement.setDouble(1, operation.getNum1());
            preparedStatement.setString(2, String.valueOf(operation.getType()));
            preparedStatement.setDouble(3, operation.getNum2());
            preparedStatement.setDouble(4, operation.getResult());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(operation.getTime()));
            preparedStatement.setString(6, operation.getUsername());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Operation> findUserOperations(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_OPERATIONS);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Operation> operationList = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                double num1 = resultSet.getDouble(2);
                String type = resultSet.getString(3);
                double num2 = resultSet.getDouble(4);
                double result = resultSet.getDouble(5);
                LocalDateTime time = resultSet.getTimestamp(6).toLocalDateTime();
                Operation operation = new Operation(id, num1, OperationType.valueOf(type), num2, result, time);
                operationList.add(operation);
            }
            Collections.sort(operationList);
            return operationList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
