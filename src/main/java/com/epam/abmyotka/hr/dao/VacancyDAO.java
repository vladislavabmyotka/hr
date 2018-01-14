package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.constant.SQLConstant;
import com.epam.abmyotka.hr.entity.Vacancy;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VacancyDAO extends AbstractDAO<Vacancy> {
    private final static Logger LOGGER = LogManager.getLogger(VacancyDAO.class);

    private static final String VACANCY_ID = "idvacancy";
    private static final String POST = "post";
    private static final String COMPANY = "company";
    private static final String SALARY = "salary";
    private static final String LOCATION = "location";
    private static final String EXPERIENCE = "experience";
    private static final String ENGLISH = "english";
    private static final String TEXT = "text";
    private static final String IS_OPEN = "isOpen";
    private static final String V_EMPLOYER_ID = "v_idEmployer";

    public VacancyDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Vacancy> findAll() {
        List<Vacancy> vacancies = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.getStatement();
            ResultSet resultSet = statement.executeQuery(SQLConstant.SQL_SELECT_ALL_VACANCY);
            while(resultSet.next()) {
                Vacancy vacancy = new Vacancy();
                vacancy.setVacancyId(resultSet.getInt(VACANCY_ID));
                vacancy.setPost(resultSet.getString(POST));
                vacancy.setCompany(resultSet.getString(COMPANY));
                vacancy.setSalary(resultSet.getBigDecimal(SALARY));
                vacancy.setLocation(resultSet.getString(LOCATION));
                vacancy.setExperience(resultSet.getInt(EXPERIENCE));
                vacancy.setEnglish(resultSet.getString(ENGLISH));
                vacancy.setText(resultSet.getString(TEXT));
                vacancy.setOpen(resultSet.getBoolean(IS_OPEN));
                vacancy.setEmployerId(resultSet.getInt(V_EMPLOYER_ID));

                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return vacancies;
    }

    @Override
    public Vacancy findById(int id) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int delete(Vacancy entity) {
        return 0;
    }

    @Override
    public int add(Vacancy entity) {
        return 0;
    }
}
