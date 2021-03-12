package com.tobispring.book;

import com.tobispring.book.application.InterfaceUserDao;
import com.tobispring.book.application.UserDao;
import com.tobispring.book.connection.BConnectionMaker;
import com.tobispring.book.connection.ConnectionMaker;
import com.tobispring.book.connection.CountingConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserDao userDao(){
        return new InterfaceUserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(new BConnectionMaker());
    }
}
