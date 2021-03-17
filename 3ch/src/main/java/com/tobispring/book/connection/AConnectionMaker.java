package com.tobispring.book.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AConnectionMaker implements ConnectionMaker {

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("A클라이언트용 커넥션 생성");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tobispring", "root", "1234");
    }
}
