package com.epam.abmyotka.hr.constant;

public class SQLConstant {
    public static final String SQL_SELECT_ALL_ACCOUNT = "SELECT * FROM account";
    public static final String SQL_SELECT_SINGLE_LOGIN = "SELECT login FROM account WHERE login = ?";
    public static final String SQL_SELECT_FIND_ACCOUNT_ID_BY_PASSWORD =
            "SELECT idAccount FROM account WHERE password = ?";
    public static final String SQL_SELECT_FIND_ACCOUNT_ID_BY_LOGIN_PASSWORD_ATTACHMENT = "SELECT idAccount " +
            "FROM account WHERE login = ? and password = ? and attachment = ?";
    public static final String SQL_INSERT_ADD_ACCOUNT =
            "INSERT INTO account (login, password, attachment) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_ACCOUNT =
            "UPDATE account SET login = ?, password = ? WHERE idAccount = ?";
    public static final String SQL_DELETE_ACCOUNT = "DELETE FROM account WHERE login = ? and password = ?";

    public static final String SQL_SELECT_ALL_CANDIDATE = "SELECT * FROM candidate";
    public static final String SQL_SELECT_CANDIDATE_BY_ACCOUNT_ID = "SELECT * FROM candidate WHERE c_idAccount = ?";
    public static final String SQL_SELECT_CANDIDATE_BY_CANDIDATE_ID = "SELECT * FROM candidate WHERE idCandidate = ?";
    public static final String SQL_DELETE_CANDIDATE_BY_ACCOUNT_ID = "DELETE FROM candidate WHERE c_idAccount = ?";
    public static final String SQL_DELETE_CANDIDATE_BY_CANDIDATE_ID = "DELETE FROM candidate WHERE idCandidate = ?";
    public static final String SQL_UPDATE_CANDIDATE = "UPDATE candidate SET surname = ?, name = ?, lastname = ?, " +
            "age = ?, email = ?, address = ?, citizenship = ?, phone = ?, post = ?, education = ?, experience = ?, " +
            "english = ?, skill = ? WHERE idCandidate = ?";
    public static final String SQL_INSERT_CANDIDATE = "INSERT INTO candidate (surname, name, lastname, age, email, " +
            "address, citizenship, phone, post, education, experience, english, skill, c_idAccount) VALUES (?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_ALL_EMPLOYER = "SELECT * FROM employer";
    public static final String SQL_SELECT_EMPLOYER_BY_ACCOUNT_ID = "SELECT * FROM employer WHERE e_idAccount = ?";
    public static final String SQL_SELECT_EMPLOYER_BY_EMPLOYER_ID = "SELECT * FROM employer WHERE idEmployer = ?";
    public static final String SQL_DELETE_EMPLOYER_BY_ACCOUNT_ID = "DELETE FROM employer WHERE e_idAccount = ?";
    public static final String SQL_DELETE_EMPLOYER_BY_EMPLOYER_ID = "DELETE FROM employer WHERE idEmployer = ?";
    public static final String SQL_UPDATE_EMPLOYER = "UPDATE employer SET surname = ?, name = ?, lastname = ?, " +
            "address = ?, phone = ?, email = ?, company = ? WHERE idEmployer = ?";

    public static final String SQL_SELECT_ALL_VACANCY = "SELECT * FROM vacancy";
}
