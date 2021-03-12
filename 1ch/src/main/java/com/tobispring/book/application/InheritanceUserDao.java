package com.tobispring.book.application;

import com.tobispring.book.domain.User;

import java.sql.*;

public abstract class InheritanceUserDao {
    public void add(User user) throws ClassNotFoundException, SQLException{
        Connection c = getConnection();

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
        Connection c = getConnection();

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
    //클라이언트의 요구에 따라 구현하고싶은 메소드
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}

class AUserDao extends InheritanceUserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("A클라이언트가 원하는 형식의 커넥션 생성");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tobispring", "root", "1234");
    }
}

class BUserDao extends InheritanceUserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("B클라이언트가 원하는 형식의 커넥션 생성");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tobispring", "root", "1234");
    }
}
