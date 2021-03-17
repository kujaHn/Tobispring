package com.tobispring.book.dao;

import com.tobispring.book.connection.ConnectionMaker;
import com.tobispring.book.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateUserDao implements UserDao{
    private JdbcTemplate jdbcTemplate;

    private ConnectionMaker connectionMaker;

    public JdbcTemplateUserDao(ConnectionMaker connectionMaker) {
        this.jdbcTemplate = new JdbcTemplate((DataSource) connectionMaker);
    }

    public void add(final User user) throws SQLException, ClassNotFoundException {
        Connection con = connectionMaker.getConnection();
        this.jdbcTemplate.update("insert into user(id, name, password) values(?,?,?)",
                user.getId(), user.getName(), user.getPassword());
    }

    public User get(final Long id) throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.getConnection();
        return this.jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return c.prepareStatement("select * from user where id = ?");
            }
        }, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                User user = new User();
                user.setId(rs.getLong("Id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.getConnection();
        this.jdbcTemplate.update("delete from user");
    }
}
