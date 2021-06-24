package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        User user1 = new User(1, "Muktar", "Kubanov", (byte) 20);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        User user2 = new User(2, "Атабек", "Исакунов", (byte) 20);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        User user3 = new User(3, "Нурпери", "Арзыматова", (byte) 19);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        User user4 = new User(4, "Айсезим", "Маматова", (byte) 19);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        ArrayList<User> users = (ArrayList<User>) userService.getAllUsers();
         for (User user:users){
             System.out.println(user);
         }

         userService.cleanUsersTable();
         userService.dropUsersTable();

    }
}
