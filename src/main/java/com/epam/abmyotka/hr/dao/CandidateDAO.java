package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.entity.Candidate;

import java.sql.Connection;
import java.util.List;

public class CandidateDAO extends AbstractDAO<Candidate> {

    public CandidateDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Candidate> findAll() {
        return null;
    }

    @Override
    public Candidate findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Candidate entity) {
        return false;
    }

    @Override
    public boolean create(Candidate entity) {
        return false;
    }

    @Override
    public Candidate update(Candidate entity) {
        return null;
    }
}
