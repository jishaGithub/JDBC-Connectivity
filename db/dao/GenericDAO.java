package com.solvd.laba.db.dao;

import java.util.ArrayList;

public interface GenericDAO<T> {
    Boolean findById(int id);
    ArrayList<T> selectAll();
    void addNewRow(T t);
    void update(T t,int id);
    void delete(int id);
}
