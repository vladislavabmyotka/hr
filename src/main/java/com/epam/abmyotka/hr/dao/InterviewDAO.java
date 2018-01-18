package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.constant.SQLConstant;
import com.epam.abmyotka.hr.entity.Interview;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterviewDAO extends AbstractDAO<Interview>{
    private final static Logger LOGGER = LogManager.getLogger(InterviewDAO.class);

    private static final String INTERVIEW_ID = "idInterview";
    private static final String CANDIDATE_ID = "i_idCandidate";
    private static final String VACANCY_ID = "i_idvacancy";
    private static final String PRE_RESULT = "preResult";
    private static final String FINAL_RESULT = "finalResult";

    public InterviewDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Interview> findAll() {
        List<Interview> interviews = new ArrayList<>();
        Statement statement = null;
        try {
            statement = this.getStatement();
            ResultSet resultSet = statement.executeQuery(SQLConstant.SQL_SELECT_ALL_INTERVIEW);
            while(resultSet.next()) {
                Interview interview = createInterviewByResultSet(resultSet);
                interviews.add(interview);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQL exception (request or table failed)! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return interviews;
    }

    @Override
    public Interview findById(int interviewId) {
        Interview interview = null;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_SELECT_INTERVIEW_BY_INTERVIEW_ID);
            statement.setInt(1, interviewId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                interview = createInterviewByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying find candidate by idInterview in database! Detail: " +
                    e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return interview;
    }

    private Interview createInterviewByResultSet(ResultSet resultSet) throws SQLException {
        Interview interview = new Interview();
        interview.setInterviewId(resultSet.getInt(INTERVIEW_ID));
        interview.setCandidateId(resultSet.getInt(CANDIDATE_ID));
        interview.setVacancyId(resultSet.getInt(VACANCY_ID));
        interview.setPreResult(resultSet.getString(PRE_RESULT));
        interview.setFinalResult(resultSet.getString(FINAL_RESULT));
        return interview;
    }

    @Override
    public int delete(int interviewId) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_DELETE_INTERVIEW_BY_INTERVIEW_ID);
            statement.setInt(1, interviewId);
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying delete interview from database! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }
        return countRowsAffected;
    }

    @Override
    public int delete(Interview entity) {
        return 0;
    }

    @Override
    public int update(Interview interview) {
        int countRowsAffected = 0;
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(SQLConstant.SQL_UPDATE_INTERVIEW);
            statement.setString(1, interview.getPreResult());
            statement.setString(2, interview.getFinalResult());
            statement.setInt(3, interview.getInterviewId());
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying update interview! Detail: " + e.getMessage());
        } finally {
            this.closeStatement(statement);
        }

        return countRowsAffected;
    }

    @Override
    public int add(Interview entity) {
        return 0;
    }
}
