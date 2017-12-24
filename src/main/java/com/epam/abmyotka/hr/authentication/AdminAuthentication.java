package com.epam.abmyotka.hr.authentication;

import com.epam.abmyotka.hr.creator.AdminCreator;
import com.epam.abmyotka.hr.entity.Account;

public class AdminAuthentication {

    public static boolean isAdmin(Account user) {
        AdminCreator creator = new AdminCreator();
        Account admin = creator.createAdmin();

        return admin.getLogin().equals(user.getLogin()) && admin.getPassword().equals(user.getPassword());
    }
}
