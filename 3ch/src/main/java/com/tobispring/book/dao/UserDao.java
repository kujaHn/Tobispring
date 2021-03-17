package com.tobispring.book.dao;

import com.tobispring.book.domain.User;

import java.sql.SQLException;

public interface UserDao {
    void add(User user) throws SQLException, ClassNotFoundException;

    User get(Long id) throws SQLException, ClassNotFoundException;

    void deleteAll() throws SQLException, ClassNotFoundException;

}
