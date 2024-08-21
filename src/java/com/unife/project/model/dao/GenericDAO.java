package com.unife.project.model.dao;

import java.util.List;

//dao generico genitore di ogni dao per definire i metodi standard CRUD (Create, Read, Update, Delete)
public interface GenericDAO<T> {
    void save(T entity);    //create
    void update(T entity);  
    void delete(T entity);
    T findById(Long id);    //read one
    List<T> findAll();      //read all
}