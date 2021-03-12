package com.tobispring.book.singleton;

import com.tobispring.book.AppConfig;
import com.tobispring.book.application.UserDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleTonTest {

    @Test
    @DisplayName("순수한 코드로 이루어진 DI 컨테이너")
    void pureContaioner1() {

        AppConfig appConfig = new AppConfig();

        // 1번 클라이언트의 요청에 따라 객체 생성
        UserDao userDao1 = appConfig.userDao();

        // 2번 클라이언트의 요청에 따라 객체 생성
        UserDao userDao2 = appConfig.userDao();

        System.out.println("userDao1 = " + userDao1);
        System.out.println("userDao2 = " + userDao2);

        //같은 인스턴스가 아니기 때문에 오류 발생
        Assertions.assertThat(userDao1).isSameAs(userDao2);

    }

    @Test
    @DisplayName("싱글톤 패턴 테스트")
    void singletonServiceTest() {

        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        Assertions.assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1번 클라이언트의 요청에 따라 객체 생성
        UserDao userDao1 = ac.getBean("userDao", UserDao.class);

        // 2번 클라이언트의 요청에 따라 객체 생성
        UserDao userDao2 = ac.getBean("userDao", UserDao.class);

        System.out.println("userDao1 = " + userDao1);
        System.out.println("userDao2 = " + userDao2);

        //같은 인스턴스가 아니기 때문에 오류 발생
        Assertions.assertThat(userDao1).isSameAs(userDao2);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}

