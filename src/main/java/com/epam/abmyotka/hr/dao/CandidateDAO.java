package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.constant.SQLConstant;
import com.epam.abmyotka.hr.entity.Candidate;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO extends AbstractDAO<Candidate> {
    private final static Logger LOGGER = LogManager.getLogger(CandidateDAO.class);

    public CandidateDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.getStatement();
            ResultSet resultSet = statement.executeQuery(SQLConstant.SQL_SELECT_ALL_CANDIDATE);
            while(resultSet.next()) {
                Candidate candidate = createCandidateByResultSet(resultSet);
                candidates.add(candidate);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return candidates;
    }

    public Candidate findByAccountId(int accountId) {
        Candidate candidate = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.getPreparedStatement(SQLConstant.SQL_SELECT_CANDIDATE_BY_ACCOUNT_ID);
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                candidate = createCandidateByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(preparedStatement);
        }

        return candidate;
    }

    private Candidate createCandidateByResultSet(ResultSet resultSet) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setCandidateId(resultSet.getInt("idCandidate"));
        candidate.setSurname(resultSet.getString("surname"));
        candidate.setName(resultSet.getString("name"));
        candidate.setLastname(resultSet.getString("lastname"));
        candidate.setAge(resultSet.getInt("age"));
        candidate.setEmail(resultSet.getString("email"));
        candidate.setAddress(resultSet.getString("address"));
        candidate.setCitizenship(resultSet.getString("citizenship"));
        candidate.setPhone(resultSet.getString("phone"));
        candidate.setPost(resultSet.getString("post"));
        candidate.setEducation(resultSet.getString("education"));
        candidate.setExperience(resultSet.getInt("experience"));
        candidate.setSkill(resultSet.getString("skill"));
        candidate.setAccountId(resultSet.getInt("c_idAccount"));

        return candidate;
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
