package com.solvd.laba.db.dao;

import com.solvd.laba.db.connection.ConfigFileDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
    protected AbstractDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    protected Connection getConnection() throws SQLException {
        return ConfigFileDAO.getDataSource().getConnection();
    }

    public abstract Boolean findById(int id);
    public abstract ArrayList<T> selectAll();
    public abstract void addNewRow(T t);
    public abstract void update(T t,int id);
    public abstract void delete(int id);
}
