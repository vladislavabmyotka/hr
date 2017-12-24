package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.entity.Employer;

import java.sql.Connection;
import java.util.List;

public class EmployerDAO extends AbstractDAO<Employer> {

    public EmployerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Employer> findAll() {
        return null;
    }

    @Override
    public Employer findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Employer entity) {
        return false;
    }

    @Override
    public boolean create(Employer entity) {
        return false;
    }

    @Override
    public Employer update(Employer entity) {
        return null;
    }
}
