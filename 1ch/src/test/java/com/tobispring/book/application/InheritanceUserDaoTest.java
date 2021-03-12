package com.tobispring.book.application;

import com.tobispring.book.application.AUserDao;
import com.tobispring.book.application.BUserDao;
import com.tobispring.book.application.InheritanceUserDao;
import com.tobispring.book.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Transactional
public class InheritanceUserDaoTest {
    InheritanceUserDao daoA = new AUserDao();
    InheritanceUserDao daoB = new BUserDao();

    @Test
    @DisplayName("상속을 통한 확장 테스트1")
    void A클라이언트() throws SQLException, ClassNotFoundException {
        //given
        User user1 = new User(1L, "테스트1", "password1");

        //when
        daoA.add(user1);
        User newUser = daoA.get(user1.getId());

        //then
        Assertions.assertThat(user1).isEqualTo(newUser);
        System.out.println(user1.getName() + " 등록 성공!");
    }

    @Test
    @DisplayName("상속을 통한 확장 테스트1")
    void B클라이언트() throws SQLException, ClassNotFoundException {
        //given
        User user1 = new User(2L, "테스트2", "password2");

        //when
        daoB.add(user1);
        User newUser = daoB.get(user1.getId());

        //then
        Assertions.assertThat(user1).isEqualTo(newUser);
        System.out.println(user1.getName() + " 등록 성공!");
    }
}
