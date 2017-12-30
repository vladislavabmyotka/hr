package com.epam.abmyotka.hr.creator;

import com.epam.abmyotka.hr.entity.Account;

import java.util.ResourceBundle;

public class AdminCreator {

    public Account createAdmin() {
        ResourceBundle resource = ResourceBundle.getBundle("admin");
        String login = resource.getString("admin.username");
        String password = resource.getString("admin.password");
        String attachment = resource.getString("admin.attachment");
        return new Account(login, password, attachment);
    }
}
