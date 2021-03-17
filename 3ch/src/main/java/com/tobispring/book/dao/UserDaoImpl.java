package com.tobispring.book.dao;

import com.tobispring.book.connection.ConnectionMaker;
import com.tobispring.book.connection.strategy.AddStatement;
import com.tobispring.book.connection.strategy.DeleteAllStatement;
import com.tobispring.book.connection.strategy.StatementStrategy;
import com.tobispring.book.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDaoImpl(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException, ClassNotFoundException  {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = connectionMaker.getConnection();
            ps = stmt.makePreparedStatement(c);
        } catch (SQLException e) {
            throw e;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    @Override
    public void add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy aStrategy = new AddStatement(user);
        jdbcContextWithStatementStrategy(aStrategy);
    }

    @Override
    public User get(Long id) throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = connectionMaker.getConnection();

            ps = c.prepareStatement("select * from user where id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            rs.next();

            User user = new User();
            user.setId(rs.getLong("Id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));

            return user;

        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }

        }

    }

    @Override
    public void deleteAll() throws SQLException, ClassNotFoundException {
        StatementStrategy dStrategy = new DeleteAllStatement();
        jdbcContextWithStatementStrategy(dStrategy);
    }



}
