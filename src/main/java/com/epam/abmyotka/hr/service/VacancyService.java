package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.DBPool;
import com.epam.abmyotka.hr.dao.VacancyDAO;
import com.epam.abmyotka.hr.entity.Vacancy;

import java.sql.Connection;
import java.util.List;

public class VacancyService {

    public List<Vacancy> takeAll() {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        List<Vacancy> vacancies = vacancyDAO.findAll();
        pool.putConnection(connection);
        return vacancies;
    }

    public Vacancy findById(int candidateId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        Vacancy candidate = vacancyDAO.findById(candidateId);
        pool.putConnection(connection);
        return candidate;
    }

    public boolean delete(int vacancyId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        int countRowsAffected = vacancyDAO.delete(vacancyId);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean update(Vacancy vacancy) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        VacancyDAO vacancyDAO = new VacancyDAO(connection);
        int countRowsAffected = vacancyDAO.update(vacancy);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }
}
