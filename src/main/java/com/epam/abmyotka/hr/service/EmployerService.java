package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.CandidateDAO;
import com.epam.abmyotka.hr.dao.DBPool;
import com.epam.abmyotka.hr.dao.EmployerDAO;

import java.sql.Connection;

public class EmployerService {

    public boolean deleteByAccountId(int accountId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        EmployerDAO employerDAO = new EmployerDAO(connection);
        int countRowsAffected = employerDAO.delete(accountId);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }
}
