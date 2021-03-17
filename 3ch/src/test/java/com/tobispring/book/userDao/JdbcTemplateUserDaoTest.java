package com.tobispring.book.userDao;

import com.tobispring.book.AppConfig;
import com.tobispring.book.connection.ConnectionMaker;
import com.tobispring.book.dao.UserDao;
import com.tobispring.book.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class JdbcTemplateUserDaoTest {
    private ConnectionMaker AConnectionMaker;
    AppConfig appConfig = new AppConfig();
    UserDao userDao = appConfig.userDao();

    @Test
    void 추가_조회() throws SQLException, ClassNotFoundException {
        User user1 = new User(1L, "TemplateTest1", "password1");
        userDao.add(user1);

        Assertions.assertThat(user1).isEqualTo(userDao.get(1L));
    }

    @Test
    void 삭제() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class,
                () -> userDao.get(1L));
    }

}
