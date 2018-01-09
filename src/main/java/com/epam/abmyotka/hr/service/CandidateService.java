package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.AccountDAO;
import com.epam.abmyotka.hr.dao.CandidateDAO;
import com.epam.abmyotka.hr.dao.DBPool;

import java.sql.Connection;

public class CandidateService {

    public boolean deleteByAccountId(int accountId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        CandidateDAO candidateDAO = new CandidateDAO(connection);
        int countRowsAffected = candidateDAO.delete(accountId);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }
}
