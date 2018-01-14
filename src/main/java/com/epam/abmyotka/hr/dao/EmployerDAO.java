package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.constant.SQLConstant;
import com.epam.abmyotka.hr.entity.Employer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployerDAO extends AbstractDAO<Employer> {
    private final static Logger LOGGER = LogManager.getLogger(EmployerDAO.class);

    private static final String EMPLOYER_ID = "idEmployer";
    private static final String SURNAME = "surname";
    private static final String NAME = "name";
    private static final String LASTNAME = "lastname";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String COMPANY = "company";
    private static final String E_ACCOUNT_ID = "e_idAccount";

    public EmployerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Employer> findAll() {
        List<Employer> employers = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.getStatement();
            ResultSet resultSet = statement.executeQuery(SQLConstant.SQL_SELECT_ALL_EMPLOYER);
            while(resultSet.next()) {
                Employer employer = createEmployerByResultSet(resultSet);
                employers.add(employer);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return employers;
    }

    public Employer findByAccountId(int accountId) {
        Employer employer = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.getPreparedStatement(SQLConstant.SQL_SELECT_EMPLOYER_BY_ACCOUNT_ID);
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employer = createEmployerByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(preparedStatement);
        }

        return employer;
    }

    private Employer createEmployerByResultSet(ResultSet resultSet) throws SQLException {
        Employer employer = new Employer();
        employer.setEmployerId(resultSet.getInt(EMPLOYER_ID));
        employer.setSurname(resultSet.getString(SURNAME));
        employer.setName(resultSet.getString(NAME));
        employer.setLastname(resultSet.getString(LASTNAME));
        employer.setAddress(resultSet.getString(ADDRESS));
        employer.setPhone(resultSet.getString(PHONE));
        employer.setEmail(resultSet.getString(EMAIL));
        employer.setCompany(resultSet.getString(COMPANY));
        employer.setAccountId(resultSet.getInt(E_ACCOUNT_ID));

        return employer;
    }

    @Override
    public Employer findById(int id) {
        return null;
    }

    @Override
    public int delete(int accountId) {
        int countRowsAffected = 0;
        try {
            PreparedStatement statement = this.getPreparedStatement(SQLConstant.SQL_DELETE_EMPLOYER_BY_ACCOUNT_ID);
            statement.setInt(1, accountId);
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying delete employer from database! Detail: " + e.getMessage());
        }
        return countRowsAffected;
    }

    @Override
    public int delete(Employer entity) {
        return 0;
    }

    @Override
    public int add(Employer entity) {
        return 0;
    }
}
