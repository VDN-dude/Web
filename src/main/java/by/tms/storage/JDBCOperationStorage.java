package by.tms.storage;

import by.tms.entity.Operation;
import by.tms.entity.OperationType;
import by.tms.entity.User;
import by.tms.util.ConnectionJDBC;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDBCOperationStorage implements OperationStorage {
    private static JDBCOperationStorage instance;
    private final List<String> tables = new ArrayList<>();
    private static final String SELECT_USER_OPERATIONS = "select * from operation_sum where userid = ? order by time desc limit 6 offset ?";
    private static final String WRITE_OPERATION = " (num1, type, num2, result, time, userid) values (?, ?, ?, ?, ?, ?)";

    private JDBCOperationStorage() {
        tables.add("operation_sum");
        tables.add("operation_sub");
        tables.add("operation_mul");
        tables.add("operation_div");
    }

    public static JDBCOperationStorage getInstance() {
        if (instance == null) {
            instance = new JDBCOperationStorage();
        }
        return instance;
    }

    @Override
    public void save(Operation operation) {
        Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
        String table = "operation_" + String.valueOf(operation.getType()).toLowerCase();
        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement("insert into " + table + WRITE_OPERATION);
            preparedStatement.setDouble(1, operation.getNum1());
            preparedStatement.setString(2, String.valueOf(operation.getType()));
            preparedStatement.setDouble(3, operation.getNum2());
            preparedStatement.setDouble(4, operation.getResult());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(operation.getTime()));
            preparedStatement.setInt(6, operation.getUser().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Operation> findByUser(User user, int paginationOffset) {
        Connection postgresConnection = ConnectionJDBC.getPostgresConnection();
        List<Operation> operationList = new ArrayList<>();
//        for (String table : tables) {
            try {
                PreparedStatement preparedStatement = postgresConnection.prepareStatement(SELECT_USER_OPERATIONS);
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2,paginationOffset);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    double num1 = resultSet.getDouble("num1");
                    double num2 = resultSet.getDouble("num2");
                    OperationType type = OperationType.valueOf(resultSet.getString("type"));
                    double result = resultSet.getDouble("result");
                    LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                    Operation operation = new Operation(id, num1, num2, type, result, user, time);
                    operationList.add(operation);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
//        }
        Collections.sort(operationList);
        return operationList;
    }
}
