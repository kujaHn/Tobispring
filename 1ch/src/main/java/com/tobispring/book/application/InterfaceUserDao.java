package com.tobispring.book.application;

import com.tobispring.book.connection.ConnectionMaker;
import com.tobispring.book.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfaceUserDao implements UserDao{

    private final ConnectionMaker connectionMaker;

    public InterfaceUserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();

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
        Connection c = connectionMaker.getConnection();

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
