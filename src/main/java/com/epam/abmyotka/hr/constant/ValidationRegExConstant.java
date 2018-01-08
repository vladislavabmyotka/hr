package com.epam.abmyotka.hr.constant;

public class ValidationRegExConstant {
    public static final String LOGIN_REGEX = "[a-zA-Z][\\w]{4,20}";
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,12}$";
}
