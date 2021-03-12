package com.tobispring.book.application;

import com.tobispring.book.AppConfig;
import com.tobispring.book.connection.CountingConnectionMaker;
import com.tobispring.book.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {

    @Test
    @DisplayName("카운트 해주는 ConnectionMaker")
    void countingTest() throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        UserDao userDao = ac.getBean("userDao", UserDao.class);

        User user1 = new User(1L, "countTest1", "password1");
        User user2 = new User(2L, "countTest1", "password2");
        User user3 = new User(3L, "countTest1", "password3");
        User user4 = new User(4L, "countTest1", "password4");

        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        userDao.add(user4);
        CountingConnectionMaker ccm = ac.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter = " + ccm.getCounter());

    }
}
