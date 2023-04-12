package by.tms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public Connection getPostgresConnection(){
        Connection connection;
        try {
             connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "0314");
             connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
