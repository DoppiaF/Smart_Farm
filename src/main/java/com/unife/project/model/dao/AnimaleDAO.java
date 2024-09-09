package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Animale;


public interface AnimaleDAO extends GenericDAO<Animale> {
    //List<Animale> findBySpecies(String species);
    //void updateHealthStatus(int id, String healthStatus);
    //List<Animale> findByAge(int age);
    List<Animale> findByStalla(String etichettaStalla);
}