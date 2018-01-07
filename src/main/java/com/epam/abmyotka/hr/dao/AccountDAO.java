package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.constant.SQLConstant;
import com.epam.abmyotka.hr.entity.Account;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    @Override
    public Account findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Account entity) {
        return false;
    }

    @Override
    public boolean create(Account entity) {
        return false;
    }

    @Override
    public Account update(Account entity) {
        return null;
    }
}
