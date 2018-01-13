package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.AccountDAO;
import com.epam.abmyotka.hr.dao.CandidateDAO;
import com.epam.abmyotka.hr.dao.DBPool;
import com.epam.abmyotka.hr.entity.Candidate;

import java.sql.Connection;
import java.util.List;

public class CandidateService {

    public List<Candidate> takeAll() {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        CandidateDAO candidateDAO = new CandidateDAO(connection);
        List<Candidate> candidates = candidateDAO.findAll();
        pool.putConnection(connection);
        return candidates;
    }

    public boolean deleteByAccountId(int accountId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        CandidateDAO candidateDAO = new CandidateDAO(connection);
        int countRowsAffected = candidateDAO.delete(accountId);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }
}
