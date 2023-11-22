package com.sacms.database;

import java.sql.ResultSet;

public interface DAO<T> {
    static final DBManager dbManager = DBManager.getInstance();

    void createTable();
    ResultSet create(T t);
    T read(int i);
    void update(T t);
    void delete(T t);

}
