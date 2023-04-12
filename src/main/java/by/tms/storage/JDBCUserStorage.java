package by.tms.storage;


import by.tms.entity.User;
import by.tms.util.ConnectionJDBC;

import java.sql.*;
import java.util.Optional;

public class JDBCUserStorage implements UserStorage{
    private static JDBCUserStorage instance;
    private final ConnectionJDBC connectionJDBC = new ConnectionJDBC();
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String WRITE_USER = "insert into users(firstname, lastname, email, username, password) values (?, ?, ?, ?, ?)";

    private JDBCUserStorage() {

    }
    public static JDBCUserStorage getInstance(){
        if (instance == null){
            instance = new JDBCUserStorage();
        }
        return instance;
    }

    public void save(User user) {
        Connection postgresConnection = connectionJDBC.getPostgresConnection();
        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(WRITE_USER);;
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByEmail(String email) {
        Connection postgresConnection = connectionJDBC.getPostgresConnection();
        try {
            Statement statement = postgresConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while(resultSet.next()){
                if(resultSet.getString("email").equals(email)){
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");

                    User user = new User(id, firstName, lastName, username, email, password);
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public boolean checkEmail(String email){
        Connection postgresConnection = connectionJDBC.getPostgresConnection();
        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SELECT_ALL_USERS + " where email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkUsername(String username){
        Connection postgresConnection = connectionJDBC.getPostgresConnection();
        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SELECT_ALL_USERS + " where username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
