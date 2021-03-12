package com.tobispring.book.application;

import com.tobispring.book.connection.AConnectionMaker;
import com.tobispring.book.domain.User;

import java.sql.*;

public class WorstUserDao {
    public WorstUserDao(){
        AConnectionMaker sqlConnection = new AConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException{
        //커넥션 생성
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("SQL 커넥션 연결 완료");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tobispring", "root", "1234");

        //데이터 입력
        PreparedStatement ps = c.prepareStatement(
                "insert into user(id, name, password) value(?, ?, ?)");
        ps.setLong(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(Long id) throws ClassNotFoundException, SQLException{
        //커넥션 생성
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("SQL 커넥션 연결 완료");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tobispring", "root", "1234");

        //데이터 조회
        PreparedStatement ps = c.prepareStatement(
                "select * from user where id = ?");
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getLong("Id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
