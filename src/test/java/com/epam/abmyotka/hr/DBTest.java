package com.epam.abmyotka.hr;

import com.epam.abmyotka.hr.dao.AccountDAO;
import com.epam.abmyotka.hr.dao.DBPool;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;

public class DBTest {

    @Test
    public void checkCoincidenceLoginTrueTest() {
        String login = "Max Direct";
        DBPool pool = DBPool.getInstance();

        Connection connection = pool.getConnection();
        AccountDAO accountDAO = new AccountDAO(connection);
        Assert.assertTrue(accountDAO.checkCoincidenceByLogin(login));
        pool.putConnection(connection);
    }

    @Test
    public void checkCoincidenceLoginFalseTest() {
        String login = "not exist login (54g535455h3df)";
        DBPool pool = DBPool.getInstance();

        Connection connection = pool.getConnection();
        AccountDAO accountDAO = new AccountDAO(connection);
        Assert.assertFalse(accountDAO.checkCoincidenceByLogin(login));
        pool.putConnection(connection);
    }
}
