package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Zona;

public interface ZonaDAO extends GenericDAO<Zona> {
    //metodi specifici per zona
    //List<Zona> findByNome(String nome);
    //List<Zona> findByCapienza(int capienza);
    //List<Zona> findByTipo(String tipo);
    //List<Zona> findByCapienzaAndTipo(int capienza, String tipo);
    //List<Zona> findByNomeAndCapienza(String nome, int capienza);
    //List<Zona> findByNomeAndTipo(String nome, String tipo);
    //List<Zona> findByNomeAndCapienzaAndTipo(String nome, int capienza, String tipo);
    
    Zona findByCoordAndPiantagione(int coordX, int coordY, int id_piantagione);
    
    List<Zona> findByPiantagione(int id_piantagione);
}
