package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.entity.Interview;

import java.sql.Connection;
import java.util.List;

public class InterviewDAO extends AbstractDAO<Interview>{

    public InterviewDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Interview> findAll() {
        return null;
    }

    @Override
    public Interview findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Interview entity) {
        return false;
    }

    @Override
    public boolean create(Interview entity) {
        return false;
    }

    @Override
    public Interview update(Interview entity) {
        return null;
    }
}
