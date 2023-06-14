package com.solvd.laba.db.dao;

import java.util.ArrayList;

public interface GenericDAO<T> {
    T get(int id);
    ArrayList<T> get();
    void add(T t);
    void update(T t,int id);
    void delete(int id);
}
