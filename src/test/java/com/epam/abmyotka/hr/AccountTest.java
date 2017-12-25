package com.epam.abmyotka.hr;

import com.epam.abmyotka.hr.creator.AdminCreator;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.service.AccountService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.naming.NamingException;
import java.sql.SQLException;

public class AccountTest {

    @Test
    public void createAdminTest() {
        AdminCreator creator = new AdminCreator();
        Account admin = creator.createAdmin();
        Account expectedAdmin = new Account("admin", "admin123");
        Assert.assertEquals(admin, expectedAdmin);
    }

    @Test
    public void findAccountTest() {
        Account expectedUser = new Account("KingOfTheDot", "qwerty1234");
        AccountService service = new AccountService();
        Account actualUser = null;
        try {
            actualUser = service.findAccount(expectedUser);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println(actualUser.getLogin() + "    " + actualUser.getPassword());
    }

    //TODO: сделать тест на аутентификацию админа
}
