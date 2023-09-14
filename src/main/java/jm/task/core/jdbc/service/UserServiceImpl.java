package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    //Переиспользовать методы UserDaoJDBCImpl
    UserDaoJDBCImpl userDaojdbc = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDaojdbc.createUsersTable();

    }

    public void dropUsersTable() {
        userDaojdbc.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaojdbc.saveUser(name, lastName, age);
    }
    public void printMessage(String name) {
        System.out.printf("User с именем – %s добавлен в базу данных\n", name);
    }

    public void removeUserById(long id) {
        userDaojdbc.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaojdbc.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaojdbc.cleanUsersTable();
    }
}
