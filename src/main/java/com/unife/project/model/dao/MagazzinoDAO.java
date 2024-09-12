package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Magazzino;

public interface MagazzinoDAO extends GenericDAO<Magazzino>{
    //meotodi specifici per magazzino
    List<Magazzino> findAllUltimoAnno();
    void eliminaMangime();
}
