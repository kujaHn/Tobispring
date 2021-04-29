package com.tobispring.book.service;

import com.tobispring.book.dao.UserDao;
import com.tobispring.book.domain.Level;
import com.tobispring.book.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
class UserServiceTest {

    @Autowired UserDao userDao;
    @Autowired UserService userService;

    List<User> users = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        users.add(new User("id1", "박범진", "p1", "testEmail1", Level.BASIC, 49, 0));
        users.add(new User("id2", "강명성", "p2", "testEmail2", Level.BASIC, 50, 0));
        users.add(new User("id3", "신승한", "p3", "testEmail3", Level.SILVER, 60, 29));
        users.add(new User("id4", "이상호", "p4", "testEmail4", Level.SILVER, 60, 30));
        users.add(new User("id5", "오민규", "p5", "testEmail5", Level.GOLD, 100, 100));
    }

    @AfterEach
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Test @DirtiesContext
    public void 자동등업_테스트() throws Exception {

        //given
        for (User user : users) {
            userDao.add(user);
        }

        //when
        userService.upgradeLevels();

        //then
        Assertions.assertThat(userDao.get(users.get(0).getId()).getLevel()).isEqualTo(Level.BASIC);
        Assertions.assertThat(userDao.get(users.get(1).getId()).getLevel()).isEqualTo(Level.SILVER);
        Assertions.assertThat(userDao.get(users.get(2).getId()).getLevel()).isEqualTo(Level.SILVER);
        Assertions.assertThat(userDao.get(users.get(3).getId()).getLevel()).isEqualTo(Level.GOLD);
        Assertions.assertThat(userDao.get(users.get(4).getId()).getLevel()).isEqualTo(Level.GOLD);
    }


    // DB에 넣는 로직 보다는, 데이터 생성 로직이기 떄문에 Service에 추가
    @Test
    public void  추가시_기본레벨은_Basic() throws Exception{
        //given

        User NoLevelUser = new User("test1", "testName1", "testPw1", "testEmail1", Level.BASIC, 49, 0);
        NoLevelUser.setLevel(null);

        //when
        if (NoLevelUser.getLevel() == null) {
            NoLevelUser.setLevel(Level.BASIC);
            userDao.add(NoLevelUser);
        }

        //then
        Assertions.assertThat(userDao.get("test1").getLevel()).isEqualTo(Level.BASIC);
    }

}