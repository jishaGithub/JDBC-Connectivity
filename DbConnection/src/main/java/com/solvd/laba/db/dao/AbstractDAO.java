package com.solvd.laba.db.dao;

import java.util.ArrayList;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
    public abstract Boolean findById(int id);
    public abstract ArrayList<T> selectAll();
    public abstract void addNewRow(T t);
    public abstract void update(T t,int id);
    public abstract void delete(int id);
}
