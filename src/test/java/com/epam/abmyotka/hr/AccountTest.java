package com.epam.abmyotka.hr;

import com.epam.abmyotka.hr.creator.AdminCreator;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.service.AccountService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTest {

    @Test
    public void createAdminTest() {
        AdminCreator creator = new AdminCreator();
        Account admin = creator.createAdmin();
        Account expectedAdmin = new Account("admin", "admin123", "a");
        Assert.assertEquals(admin, expectedAdmin);
    }

    @Test
    public void findAccountTest() {
        Account expectedUser = new Account(3, "KingOfTheDot", "qwerty1234", "c");
        AccountService service = new AccountService();
        Account actualUser = service.find(expectedUser);
        Assert.assertEquals(actualUser, expectedUser);
    }
}
