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
                vacancy.setVacancyId(resultSet.getInt("idvacancy"));
                vacancy.setPost(resultSet.getString("post"));
                vacancy.setCompany(resultSet.getString("company"));
                vacancy.setSalary(resultSet.getBigDecimal("salary"));
                vacancy.setLocation(resultSet.getString("location"));
                vacancy.setExperience(resultSet.getInt("experience"));
                vacancy.setText(resultSet.getString("text"));
                vacancy.setOpen(resultSet.getBoolean("isOpen"));
                vacancy.setEmployerId(resultSet.getInt("v_idEmployer"));
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
    public Vacancy findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Vacancy entity) {
        return false;
    }

    @Override
    public boolean add(Vacancy entity) {
        return false;
    }

    @Override
    public Vacancy update(Vacancy entity) {
        return null;
    }
}
