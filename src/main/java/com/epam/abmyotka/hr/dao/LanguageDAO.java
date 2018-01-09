package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.constant.SQLConstant;
import com.epam.abmyotka.hr.entity.Language;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LanguageDAO extends AbstractDAO<Language> {
    private final static Logger LOGGER = LogManager.getLogger(LanguageDAO.class);

    public LanguageDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Language> findAll() {
        return null;
    }

    @Override
    public Language findEntityById(int id) {
        return null;
    }

    @Override
    public int delete(int candidateId) {
        int countRowsAffected = 0;
        try {
            PreparedStatement statement =
                    this.getPreparedStatement(SQLConstant.SQL_DELETE_CANDIDATE_HAS_LANGUAGE_BY_CANDIDATE_ID);
            statement.setInt(1, candidateId);
            countRowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error while trying delete row in the table 'candidate_has_language' from " +
                    "database! Detail: " + e.getMessage());
        }
        return countRowsAffected;
    }

    @Override
    public int delete(Language entity) {
        return 0;
    }

    @Override
    public int add(Language entity) {
        return 0;
    }
}
