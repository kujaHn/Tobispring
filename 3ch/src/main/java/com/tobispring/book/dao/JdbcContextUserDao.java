package com.tobispring.book.dao;

import com.tobispring.book.JdbcContext;
import com.tobispring.book.connection.strategy.StatementStrategy;
import com.tobispring.book.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContextUserDao implements UserDao{
    private JdbcContext jdbcContext;

    public JdbcContextUserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    @Override
    public void add(final User user) throws SQLException, ClassNotFoundException {
        // 템플릿 생성
        jdbcContext.workWithStatementStrategy(
                // 익명의 콜백
            new StatementStrategy() {
                public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                    PreparedStatement ps = c.prepareStatement(
                            "insert into user(id, name, password) value(?, ?, ?)");
                    ps.setLong(1, user.getId());
                    ps.setString(2, user.getName());
                    ps.setString(3, user.getPassword());

                    return ps;
                }
            }
        );
    }

    @Override
    public User get(Long id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void deleteAll() throws SQLException, ClassNotFoundException {
            jdbcContext.executeSql("delete from users");
    }
}
