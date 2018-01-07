package com.epam.abmyotka.hr.dao;

import com.epam.abmyotka.hr.entity.Language;

import java.sql.Connection;
import java.util.List;

public class LanguageDAO extends AbstractDAO<Language> {

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
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Language entity) {
        return false;
    }

    @Override
    public boolean add(Language entity) {
        return false;
    }

    @Override
    public Language update(Language entity) {
        return null;
    }
}
