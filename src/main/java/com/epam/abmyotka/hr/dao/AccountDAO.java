package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.constant.SQLConstant;
import com.epam.abmyotka.hr.entity.Account;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends AbstractDAO<Account> {
    private final static Logger LOGGER = LogManager.getLogger(AccountDAO.class);

    public AccountDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.getStatement();
            ResultSet resultSet = statement.executeQuery(SQLConstant.SQL_SELECT_ALL_ACCOUNT);
            while(resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt("idAccount"));
                account.setLogin(resultSet.getString("login"));
                account.setPassword(resultSet.getString("password"));
                account.setAttachment(resultSet.getString("attachment"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return accounts;
    }

    public boolean checkCoincidenceByLogin(String login) {
        boolean isCoincidence = true;
        try {
            PreparedStatement statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_SINGLE_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            isCoincidence = resultSet.next();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find coincidence login! Detail: " + e.getMessage());
        }
        return isCoincidence;
    }

    @Override
    public Account findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Account account) {
        return false;
    }

    @Override
    public boolean add(Account account) {
        int countRowsAffected = 0;
        try {
            PreparedStatement statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_ADD_ACCOUNT);
            statement.setString(1, account.getLogin());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getAttachment());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying add account into database! Detail: " + e.getMessage());
        }

        if (countRowsAffected != 0) {
            return true;
        } else {
            LOGGER.log(Level.ERROR, "The error occurred! Account was not added!");
            return false;
        }
    }

    @Override
    public Account update(Account account) {
        return null;
    }
}
