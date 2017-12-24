package com.epam.abmyotka.hr;

import com.epam.abmyotka.hr.creator.AdminCreator;
import com.epam.abmyotka.hr.entity.Account;
import org.testng.annotations.Test;

public class CreateAdminTest {

    @Test
    public void createAdminTest() {
        AdminCreator creator = new AdminCreator();
        Account admin = creator.createAdmin();
        System.out.println(admin.getLogin() + "\n" + admin.getPassword());
        //TODO: sdelat' norm test
    }

    //TODO: сделать тест на аутентификацию админа

    //TODO: сделать тест на поиск аккаунта
}
