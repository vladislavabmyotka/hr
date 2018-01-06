package com.epam.abmyotka.hr.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountValidator {

    public boolean checkLogin(String login) {
        Pattern pattern = Pattern.compile("[a-zA-Z][\\w]{4,20}");
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public boolean checkPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])[0-9a-z]{8,12}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
