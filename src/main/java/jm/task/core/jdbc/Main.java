package jm.task.core.jdbc;

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


        String name1 = "Kseniya", name2 = "Lim", name3 = "Alex", name4 = "Nataly";
        String lastName1 = "Vlasova", lastName2 = "Black", lastName3 = "Potter", lastName4 = "Harrison";
        Byte age1 = 15, age2 = 22, age3 = 57, age4 = 36;

        userService.saveUser(name1, lastName1, age1); //1
        userService.printMessage(name1);

        userService.saveUser(name2, lastName2, age2); //2
        userService.printMessage(name2);

        userService.saveUser(name3, lastName3, age3); //3
        userService.printMessage(name3);
        
        userService.saveUser(name4, lastName4, age4); //4
        userService.printMessage(name4);

        // Получение всех User из базы и вывод в консоль
        // ( должен быть переопределен toString в классе User)
        userService.getAllUsers().forEach(System.out::println);

        // Очистка таблицы User(ов)
        userService.cleanUsersTable();

        // Удаление таблицы
        userService.dropUsersTable();

    }

}
