package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.AccountDAO;
import com.epam.abmyotka.hr.entity.Account;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class AccountService {

    public Account findAccount(Account user) throws NamingException, SQLException{
        Properties properties = new Properties();

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        InitialContext initContext= new InitialContext(properties);
        //DataSource dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/hr");

        String jndiName = "hr";
        ConnectionPoolDataSource dataSource = (ConnectionPoolDataSource) initContext.lookup(jndiName);
        PooledConnection pooledConnection = dataSource.getPooledConnection();

        //Connection connection = dataSource.getConnection();
        Connection connection = pooledConnection.getConnection();

        AccountDAO accountDAO = new AccountDAO(connection);
        List<Account> accounts = accountDAO.findAll();

        Account role = null;

        for(Account account : accounts) {
            if (account.getLogin().equals(user.getLogin()) && account.getPassword().equals(user.getPassword())) {
                role = account;
            }
        }

        connection.close();
        return role;
    }
}
