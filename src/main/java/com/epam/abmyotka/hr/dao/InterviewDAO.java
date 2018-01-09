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
    public int delete(int id) {
        return 0;
    }

    @Override
    public int delete(Interview entity) {
        return 0;
    }

    @Override
    public int add(Interview entity) {
        return 0;
    }
}
