package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.entity.Account;

import java.sql.Connection;
import java.util.List;

public class AccountDAO extends AbstractDAO<Account> {

    public AccountDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Account> findAll() {
        return null;
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
