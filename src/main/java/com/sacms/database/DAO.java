package com.sacms.database;

import com.sacms.models.Student;

public interface DAO<T> {
    static final DBManager dbManager = DBManager.getInstance();

    void create(T t);
    T read(int i);
    void update(T t);
    void delete(T t);

}
