package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        //В методе main класса Main должны происходить следующие операции:

        UserServiceImpl userService = new UserServiceImpl();

        // Создание таблицы User(ов)
        userService.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль
        // ( User с именем – name добавлен в базу данных )

        User user1 = new User("Kseniya", "Vlasova", (byte) 15);

        User user2 = new User("Lim", "Black", (byte) 22);

        User user3 = new User("Alex", "Potter", (byte) 57);

        User user4 = new User("Nataly", "Harrison", (byte) 36);


        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge()); //1
        userService.printMessage(user1.getName());

        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge()); //2
        userService.printMessage(user2.getName());

        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge()); //3
        userService.printMessage(user3.getName());

        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge()); //4
        userService.printMessage(user4.getName());

        // Получение всех User из базы и вывод в консоль
        // ( должен быть переопределен toString в классе User)
        userService.getAllUsers().forEach(System.out::println);

        // Очистка таблицы User(ов)
        userService.cleanUsersTable();

        // Удаление таблицы
        userService.dropUsersTable();

        //Закрытие соединения/сессии
        userService.close();

    }

}
