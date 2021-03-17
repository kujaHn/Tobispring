package com.tobispring.book;

import com.tobispring.book.dao.UserDao;
import com.tobispring.book.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class DeleteTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    UserDao userDao = ac.getBean("userDao", UserDao.class);

    @Test
    public void add() throws SQLException, ClassNotFoundException {

        User user = new User(1L, "Test1", "Password1");
        userDao.add(user);

    }
    @Test
    public void 삭제테스트() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();

    }
}
