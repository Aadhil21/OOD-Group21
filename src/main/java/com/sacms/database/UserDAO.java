package com.sacms.database;

import java.sql.ResultSet;

public abstract class UserDAO<T> implements DAO<T> {
    private static final DBManager dbManager = DBManager.getInstance();

    @Override
    public ResultSet create(T t) {
        return null;
    }

    @Override
    public T read(int i) {
        return null;
    }

    @Override
    public void update(T t) {
    }

    @Override
    public void delete(T t) {
    }
}
