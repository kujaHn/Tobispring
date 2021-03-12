package com.tobispring.book.beanfind;

import com.tobispring.book.connection.AConnectionMaker;
import com.tobispring.book.connection.BConnectionMaker;
import com.tobispring.book.connection.ConnectionMaker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextExtendsFindTest {

    @Configuration
    static class TestConfig {
        @Bean
        public ConnectionMaker aConnectionMaker() {
            return new AConnectionMaker();
        }

        @Bean
        public ConnectionMaker bConnectionMaker() {
            return new BConnectionMaker();
        }
    }

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("1. 부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복오류 발생")
    void findBeanByParentTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(ConnectionMaker.class));
    }

    @Test
    @DisplayName("2. 1의 경우 타입과 같은 경우로 Bean의 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName() {
        ConnectionMaker aBean = ac.getBean("aConnectionMaker", ConnectionMaker.class);
        ConnectionMaker bBean = ac.getBean("bConnectionMaker", ConnectionMaker.class);
        assertThat(aBean).isInstanceOf(AConnectionMaker.class);
        assertThat(bBean).isInstanceOf(BConnectionMaker.class);
    }

    //별로 추천하는 방법은 아님.
    @Test
    @DisplayName("특정 하위타입으로 조회")
    void findBeanBySubType() {
        AConnectionMaker aBean = ac.getBean(AConnectionMaker.class);
        assertThat(aBean).isInstanceOf(AConnectionMaker.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findBeanByParentType() {
        Map<String, ConnectionMaker> beansOfType = ac.getBeansOfType(ConnectionMaker.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " / value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회 (Object ver.)")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " / value = " + beansOfType.get(key));
        }
    }
}
