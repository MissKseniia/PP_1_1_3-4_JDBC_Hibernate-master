package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.Util;
public class UserDaoJDBCImpl implements UserDao {
    //Обработка всех исключений должна быть здесь
    Util util = new Util();
    private final Connection connection = util.config();
    private final String tableName = "User";

    public UserDaoJDBCImpl() {
        //должен иметь конструктор по умолчанию
    }

    public void createUsersTable() {

        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + tableName +
                    " (User_id int primary key auto_increment, " +
                    "userName varchar(10), " +
                    "userLastName varchar(15), " +
                    "userAge int)";
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try {
            Statement statement = connection.createStatement();
            String query = "drop table if exists " + tableName;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            String query = "insert into " + tableName + " (userName, userLastName, userAge) " +
                    "values ('"
            + name + "', '" + lastName + "', " + age + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void removeUserById(long id) {

        try {
            Statement statement = connection.createStatement();
            String query = "delete from " + tableName + " where user_id = " + id;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "select userName, userLastName, userAge from " + tableName;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                userList.add(new User(rs.getString("userName"),
                                      rs.getString("userLastName"),
                                        rs.getByte("userAge")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {

        try {
            Statement statement = connection.createStatement();
            String query = "delete from " + tableName;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
