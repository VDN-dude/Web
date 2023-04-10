package by.tms.storage;


import by.tms.entity.User;

import java.sql.*;
import java.util.Optional;

public class JDBCUserStorage implements UserStorage{
    private static JDBCUserStorage instance;
    private final Connection connection;
    private static final String POSTGRESQL_USER = "postgres";
    private static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String POSTGRESQL_PASSWORD = "0314";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String WRITE_USER = "insert into users(firstname, lastname, email, username, password) values (?, ?, ?, ?, ?)";

    private JDBCUserStorage() {
        try {
            this.connection = DriverManager.getConnection(POSTGRESQL_URL, POSTGRESQL_USER, POSTGRESQL_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static JDBCUserStorage getInstance(){
        if (instance == null){
            instance = new JDBCUserStorage();
        }
        return instance;
    }

    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(WRITE_USER);;
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
        try {
            Statement statement = connection.createStatement();
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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS + " where email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkUsername(String username){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS + " where username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
