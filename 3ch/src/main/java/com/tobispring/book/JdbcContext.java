package com.tobispring.book;

import com.tobispring.book.connection.ConnectionMaker;
import com.tobispring.book.connection.strategy.StatementStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {

    final private ConnectionMaker connectionMaker;

    public JdbcContext(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void executeSql(final String query) throws SQLException, ClassNotFoundException {
        workWithStatementStrategy(
                new StatementStrategy() {
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        return c.prepareStatement(query);
                    }
                }
        );
    }

    public void workWithStatementStrategy(StatementStrategy strategy) throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = this.connectionMaker.getConnection();
            ps = strategy.makePreparedStatement(c);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
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
}
