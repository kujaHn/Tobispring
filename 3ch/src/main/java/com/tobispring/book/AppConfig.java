package com.tobispring.book;

import com.tobispring.book.connection.AConnectionMaker;
import com.tobispring.book.connection.ConnectionMaker;
import com.tobispring.book.dao.JdbcContextUserDao;
import com.tobispring.book.dao.JdbcTemplateUserDao;
import com.tobispring.book.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public UserDao userDao(){
//        return new UserDaoImpl(connectionMaker());
        return new JdbcTemplateUserDao(connectionMaker());
//        return new JdbcTemplateUserDao(dataSource());
    }

    @Bean
    public JdbcContext jdbcContext(){
        return new JdbcContext(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new AConnectionMaker();
    }

/*    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriver(new com.mysql.jdbc.cj.Driver());
        dataSource.setUrl("jdbc:mysql://localhost:3306/tobispring");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        return dataSource;
    }*/

}
