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
    public static final String SQL_INSERT_EMPLOYER = "INSERT INTO employer (surname, name, lastname, address, phone, " +
            "email, company, e_idAccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_ALL_VACANCY = "SELECT * FROM vacancy";
    public static final String SQL_SELECT_VACANCY_BY_VACANCY_ID = "SELECT * FROM vacancy WHERE idvacancy = ?";
    public static final String SQL_SELECT_VACANCY_BY_EMPLOYER_ID = "SELECT * FROM vacancy WHERE v_idEmployer = ?";
    public static final String SQL_DELETE_VACANCY_BY_VACANCY_ID = "DELETE FROM vacancy WHERE idvacancy = ?";
    public static final String SQL_UPDATE_VACANCY = "UPDATE vacancy SET post = ?, company = ?, salary = ?, " +
            "location = ?, experience = ?, english = ?, text = ?, conditionVacancy = ? WHERE idvacancy = ?";
    public static final String SQL_INSERT_VACANCY = "INSERT INTO vacancy (post, company, salary, location, " +
            "experience, english, text, conditionVacancy, v_idEmployer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_ALL_INTERVIEW = "SELECT * FROM interview";
    public static final String SQL_SELECT_INTERVIEW_BY_INTERVIEW_ID = "SELECT * FROM interview WHERE idInterview = ?";
    public static final String SQL_SELECT_INTERVIEW_BY_CANDIDATE_ID_VACANCY_ID = "SELECT * FROM interview " +
            "WHERE i_idCandidate = ? and i_idvacancy = ?";
    public static final String SQL_DELETE_INTERVIEW_BY_INTERVIEW_ID = "DELETE FROM interview WHERE idInterview = ?";
    public static final String SQL_UPDATE_INTERVIEW = "UPDATE interview SET preResult = ?, finalResult = ? " +
            "WHERE idInterview = ?";
    public static final String SQL_INSERT_INTERVIEW = "INSERT INTO interview (i_idCandidate, i_idvacancy) " +
            "VALUES (?, ?)";
}
