package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String databaseName = "user";
    private final String mySqlUrl = "jdbc:mysql://localhost:/" + databaseName;
    private final String userName = "root";
    private final String password = "root";
    private Connection connection;
    public Connection config() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(mySqlUrl, userName, password);
            //System.out.println("Connected");

        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
