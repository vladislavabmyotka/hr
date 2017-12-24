package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.entity.Entity;
import com.epam.abmyotka.hr.exception.SQLTechnicalException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <T extends Entity> {
    private final static Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll();
    public abstract T findEntityById(int id);
    public abstract boolean delete(int id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);

    public Statement getStatement() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLTechnicalException("Connection or Statement is null");
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(query);
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLTechnicalException("Connection or PreparedStatement is null");
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "It's impossible to close 'Statement' object!");
            }
        }
    }
}
