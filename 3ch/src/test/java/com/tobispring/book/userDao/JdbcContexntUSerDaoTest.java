package com.tobispring.book.userDao;

import com.tobispring.book.AppConfig;
import com.tobispring.book.dao.UserDao;
import com.tobispring.book.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class JdbcContexntUSerDaoTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    UserDao userDao = ac.getBean("userDao", UserDao.class);

    @Test
    @DisplayName("추가테스트_입력")
    void add() throws SQLException, ClassNotFoundException {
        User user = new User(1L, "JdbcTest1", "Password1");
        userDao.add(user);

        Assertions.assertThat(1L).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("추가테스트_삭제")
    void deleteAll() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
    }
}
