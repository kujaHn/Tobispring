package com.tobispring.book.dao;

import com.tobispring.book.connection.ConnectionMaker;
import com.tobispring.book.connection.strategy.AddStatement;
import com.tobispring.book.connection.strategy.StatementStrategy;
import com.tobispring.book.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InnerUserDao implements UserDao {
    private final ConnectionMaker connectionMaker;
    public InnerUserDao(ConnectionMaker connectionMaker) {
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
    public void add(final User user) throws SQLException, ClassNotFoundException {
        class InnerAddStatement implements StatementStrategy{
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement(
                        "insert into user(id, name, password) value(?, ?, ?)");
                ps.setLong(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());

                return ps;
            }
        }
        StatementStrategy aStrategy = new AddStatement(user);
        jdbcContextWithStatementStrategy(aStrategy);
    }

    @Override
    public User get(Long id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void deleteAll() throws SQLException, ClassNotFoundException {
        class InnerAddStatement implements StatementStrategy{
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                return c.prepareStatement("delete from user");
            }
        }
    }
}
