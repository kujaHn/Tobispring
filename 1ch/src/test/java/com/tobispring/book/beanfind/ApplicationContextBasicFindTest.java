package com.tobispring.book.beanfind;

import com.tobispring.book.AppConfig;
import com.tobispring.book.application.InterfaceUserDao;
import com.tobispring.book.application.UserDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        UserDao userDao = ac.getBean("userDao", UserDao.class);
        assertThat(userDao).isInstanceOf(InterfaceUserDao.class);
    }

    @Test
    @DisplayName("타입만으로 조회")
    void findBeanByType() {
        UserDao userDao = ac.getBean(UserDao.class);
        assertThat(userDao).isInstanceOf(InterfaceUserDao.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByImplType() {
        InterfaceUserDao userDao = ac.getBean("userDao", InterfaceUserDao.class);
        assertThat(userDao).isInstanceOf(InterfaceUserDao.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", UserDao.class));
    }
}
