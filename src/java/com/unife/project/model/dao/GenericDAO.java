package com.unife.project.model.dao;

import java.util.List;

//dao generico genitore di ogni dao per definire i metodi standard
public interface GenericDAO<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(Long id);
    List<T> findAll();
}