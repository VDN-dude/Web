package by.tms.storage;

import by.tms.entity.Operation;
import by.tms.entity.OperationType;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDBCOperationStorage implements OperationStorage{
    private final Connection connection;
    private static final String POSTGRESQL_USER = "postgres";
    private static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String POSTGRESQL_PASSWORD = "0314";
    private static final String SELECT_USER_OPERATIONS = "select * from operation where userid = ?";
    private static final String WRITE_OPERATION = "insert into operation(num1, type, num2, result, time, userid) values (?, ?, ?, ?, ?, ?)";

    public JDBCOperationStorage() {
        try {
            this.connection = DriverManager.getConnection(POSTGRESQL_URL, POSTGRESQL_USER, POSTGRESQL_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void save(Operation operation) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(WRITE_OPERATION);
            preparedStatement.setDouble(1, operation.getNum1());
            preparedStatement.setString(2, String.valueOf(operation.getType()));
            preparedStatement.setDouble(3, operation.getNum2());
            preparedStatement.setDouble(4, operation.getResult());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(operation.getTime()));
            preparedStatement.setInt(6, operation.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Operation> findByUserId(int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_OPERATIONS);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Operation> operationList = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                double num1 = resultSet.getDouble("num1");
                double num2 = resultSet.getDouble("num2");
                OperationType type = OperationType.valueOf(resultSet.getString("type"));
                double result = resultSet.getDouble("result");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                Operation operation = new Operation(id, num1, num2, type, result, userId, time);
                operationList.add(operation);
            }
            Collections.sort(operationList);
            return operationList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
