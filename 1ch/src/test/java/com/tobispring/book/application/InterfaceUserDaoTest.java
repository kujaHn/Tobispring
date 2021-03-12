package com.tobispring.book.application;

import com.tobispring.book.AppConfig;
import com.tobispring.book.application.InterfaceUserDao;
import com.tobispring.book.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfaceUserDaoTest {

    UserDao userDao;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        userDao = appConfig.userDao();
    }

    @Test
    @DisplayName("인터페이스를 이용한 add()메소드")
    void 인터페이스_테스트() throws SQLException, ClassNotFoundException {
        //given
        User userA = new User(1L, "InterfaceTestA", "passwordA");
        userDao.add(userA);
        //when

        User newUserA = userDao.get(userA.getId());
        //then
        Assertions.assertThat(userA).isEqualTo(newUserA);

    }
}
