package com.sacms.database;

import com.sacms.models.Student;

public abstract class UserDAO<T> implements DAO<T> {
    private static final DBManager dbManager = DBManager.getInstance();

    @Override
    public void create(T t) {
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
