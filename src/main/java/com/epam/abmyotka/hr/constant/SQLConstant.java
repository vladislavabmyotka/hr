package com.epam.abmyotka.hr.constant;

public class SQLConstant {
    public static final String SQL_SELECT_ALL_ACCOUNT = "SELECT * FROM account";
    public static final String SQL_SELECT_SINGLE_LOGIN = "SELECT login FROM account WHERE login = ?";
    public static final String SQL_SELECT_ADD_ACCOUNT =
            "INSERT INTO account (login, password, attachment) VALUES (?, ?, ?)";
    public static final String SQL_SELECT_FIND_ACCOUNT_ID_BY_PASSWORD =
            "SELECT idAccount FROM account WHERE password = ?";
    public static final String SQL_SELECT_UPDATE_ACCOUNT =
            "UPDATE account SET login = ?, password = ? WHERE idAccount = ?";

    public static final String SQL_SELECT_ALL_CANDIDATE = "SELECT * FROM candidate";
    public static final String SQL_SELECT_CANDIDATE_BY_ACCOUNT_ID = "SELECT * FROM candidate WHERE c_idAccount = ?";

    public static final String SQL_SELECT_ALL_EMPLOYER = "SELECT * FROM employer";
    public static final String SQL_SELECT_EMPLOYER_BY_ACCOUNT_ID = "SELECT * FROM employer WHERE e_idAccount = ?";

    public static final String SQL_SELECT_ALL_VACANCY = "SELECT * FROM vacancy";


}
