package com.tobispring.book.application;

import com.tobispring.book.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface UserDao {
    void add(User user) throws ClassNotFoundException, SQLException;
    User get(Long id) throws ClassNotFoundException, SQLException;
}
