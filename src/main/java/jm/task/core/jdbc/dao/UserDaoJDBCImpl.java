package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();
    private final String tableName = Util.getTableName();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {

            String query = "create table if not exists " + tableName +
                    " (user_id bigint primary key auto_increment, " +
                    "user_name varchar (10), " + "last_name varchar (15), " +
                    "user_age tinyint)";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        try (Statement statement = connection.createStatement()) {

            String query = "drop table if exists " + tableName;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String sql = "insert into " + tableName + " (user_name, last_name, user_age) values (?, ?, ?) ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {

        try (PreparedStatement statement = connection.prepareStatement(
                "delete from " + tableName + " where user_id = ?")) {

            statement.setLong(1, Math.toIntExact(id));
            statement.executeUpdate();
        } catch (SQLException | ArithmeticException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            String query = "select * from " + tableName;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                User user = new User(rs.getString("user_name"),
                                    rs.getString("last_name"),
                                    rs.getByte("user_age"));

                user.setId(rs.getLong("user_id"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {

        try (Statement statement = connection.createStatement()) {

            String query = "delete from " + tableName;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
