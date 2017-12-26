package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.AccountDAO;
import com.epam.abmyotka.hr.dao.DBPool;
import com.epam.abmyotka.hr.entity.Account;

import java.sql.Connection;
import java.util.List;

public class AccountService {

    public Account findAccount(Account user) {
        DBPool pool = DBPool.getInstance();

        Connection connection = pool.getConnection();

        AccountDAO accountDAO = new AccountDAO(connection);
        List<Account> accounts = accountDAO.findAll();

        Account role = null;

        for(Account account : accounts) {
            if (account.getLogin().equals(user.getLogin()) && account.getPassword().equals(user.getPassword())) {
                role = account;
            }
        }

        pool.putConnection(connection);
        return role;
    }
}
