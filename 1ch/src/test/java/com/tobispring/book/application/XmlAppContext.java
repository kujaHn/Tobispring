package com.tobispring.book.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

        UserDao userDao = ac.getBean("userDao", UserDao.class);
        Assertions.assertThat(userDao).isInstanceOf(UserDao.class);
    }
}
