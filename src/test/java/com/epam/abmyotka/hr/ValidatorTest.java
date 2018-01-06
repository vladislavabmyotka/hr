package com.epam.abmyotka.hr;

import com.epam.abmyotka.hr.validator.AccountValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidatorTest {

    @Test
    public void correctLoginTest() {
        String login = "simple123";
        Assert.assertTrue(AccountValidator.checkLogin(login));
    }

    @Test
    public void incorrectLoginTest() {
        String login = "656565";
        Assert.assertFalse(AccountValidator.checkLogin(login));
    }

    @Test
    public void nullLoginTest() {
        String login = null;
        Assert.assertFalse(AccountValidator.checkLogin(login));
    }

    @Test
    public void correctPasswordTest() {
        String password = "password123";
        Assert.assertTrue(AccountValidator.checkPassword(password));
    }

    @Test
    public void incorrectPasswordTest() {
        String password = "123123123";
        Assert.assertFalse(AccountValidator.checkPassword(password));
    }

    @Test
    public void nullPasswordTest() {
        String password = null;
        Assert.assertFalse(AccountValidator.checkLogin(password));
    }
}
