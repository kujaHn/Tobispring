package com.tobispring.book.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class SqlConnectionMaker implements ConnectionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("SQL 커넥션 연결 완료");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tobispring", "root", "1234");
    }
}
