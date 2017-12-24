package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.AccountDAO;
import com.epam.abmyotka.hr.dao.CandidateDAO;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.entity.Entity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private static final String CANDIDATE = "c";
    private static final String EMPLOYER = "e";

    public Entity findAccount(Account user) throws NamingException, SQLException {
        Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
        DataSource dataSource = (DataSource) envCtx.lookup("jdbc/hr");
        Connection connection = dataSource.getConnection();

        AccountDAO accountDAO = new AccountDAO(connection);
        List<Account> accounts = accountDAO.findAll();
        Entity role = null;

        for(Account account : accounts) {
            if (account.getLogin().equals(user.getLogin()) && account.getPassword().equals(user.getPassword())) {
                switch (account.getAttachment()) {
                    case CANDIDATE:
                        CandidateDAO candidateDAO = new CandidateDAO(connection);
                        role = candidateDAO.findByAccountId(account.getAccountId());
                        break;
                    case EMPLOYER:

                }
            }
        }

        connection.close();
        return role;
    }
}
