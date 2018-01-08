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
        employer.setEmployerId(resultSet.getInt("idEmployer"));
        employer.setSurname(resultSet.getString("surname"));
        employer.setName(resultSet.getString("name"));
        employer.setLastname(resultSet.getString("lastname"));
        employer.setAddress(resultSet.getString("address"));
        employer.setPhone(resultSet.getString("phone"));
        employer.setEmail(resultSet.getString("email"));
        employer.setCompany(resultSet.getString("company"));
        employer.setAccountId(resultSet.getInt("e_idAccount"));

        return employer;
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
    public int add(Employer entity) {
        return false;
    }

    @Override
    public Employer update(String field) {
        return null;
    }
}
