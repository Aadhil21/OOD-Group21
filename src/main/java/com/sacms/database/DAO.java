package com.sacms.database;

public interface DAO<T> {
    void createTable();
    void create(T t);
}
