package com.solvd.laba.db.dao;

import com.solvd.laba.db.connection.ConfigFileDAO;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
    protected AbstractDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    protected Connection getConnection() throws SQLException {
        return ConfigFileDAO.getDataSource().getConnection();
    }
}
