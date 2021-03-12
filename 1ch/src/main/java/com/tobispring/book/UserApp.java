package com.tobispring.book;

import com.tobispring.book.application.UserDao;
import com.tobispring.book.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserApp {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = ac.getBean("userDao", UserDao.class);

        User user = new User(1L, "UserA", "PasswordA");
        userDao.add(user);

        User newUser = userDao.get(1L);
        System.out.println("user = " + user);
        System.out.println("newUser = " + newUser);
    }
}
