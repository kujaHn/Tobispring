package com.tobispring.book;

import com.tobispring.book.dao.UserDao;
import com.tobispring.book.dao.UserDaoJdbc;
import com.tobispring.book.service.UserService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public UserService userService() {
        return new UserService(userDao(), transactionManager());
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoJdbc(new JdbcTemplate(dataSource()));
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager();
    }
}
