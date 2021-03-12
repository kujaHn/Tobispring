package com.tobispring.book.beanfind;

import com.tobispring.book.AppConfig;
import com.tobispring.book.connection.AConnectionMaker;
import com.tobispring.book.connection.ConnectionMaker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextSameBeanFindTest {

    //중복 테스트를 위한 설정정보
    @Configuration
    static class TestConfig {
        @Bean
        public ConnectionMaker connectionMaker1() {
            return new AConnectionMaker();
        }

        @Bean
        public ConnectionMaker connectionMaker2() {
            return new AConnectionMaker();
        }
    }

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("1. 타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복오류 발생.")
    void findBeanByTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(ConnectionMaker.class));
    }

    @Test
    @DisplayName("2. 1번 오류 시, 빈 이름을 지정하자.")
    void findBeanByName() {
        ConnectionMaker connectionMaker = ac.getBean("connectionMaker1", ConnectionMaker.class);
        assertThat(connectionMaker).isInstanceOf(ConnectionMaker.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, ConnectionMaker> beansOfType = ac.getBeansOfType(ConnectionMaker.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "/ value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }
}
