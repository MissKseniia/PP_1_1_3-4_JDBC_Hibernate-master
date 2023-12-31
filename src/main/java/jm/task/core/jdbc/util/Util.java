package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String databaseName = "user";
    private static final String mySqlUrl = "jdbc:mysql://localhost:/" + databaseName;
    private static final String userName = "root";
    private static final String password = "root";
    private static final String tableName = "User";

    public static String getTableName() {
        return tableName;
    }

    //SQL
    public static Connection getConnection() {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(mySqlUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Hibernate
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        try {

            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, mySqlUrl + "?autoReconnect=true&useSSL=false");
            settings.put(Environment.USER, userName);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(Environment.SHOW_SQL, "false");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "");

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            System.out.println("Some problems with creating the session");
            e.printStackTrace();
        }
        return sessionFactory;
    }


}
