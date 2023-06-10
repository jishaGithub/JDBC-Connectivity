package com.solvd.laba.db.service;

public interface DAOService<T> {
    void findById(int id);
    void selectAll();
    void newRow(T t);
    void updateRow(T t, int id);
    void deleteRow(int id);
}
