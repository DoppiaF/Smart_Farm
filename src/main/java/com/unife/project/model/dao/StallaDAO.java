package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Stalla;

public interface StallaDAO extends GenericDAO<Stalla>{
    public Stalla findByEtichetta(String etichettaStalla);
    public List<String> findAllEtichette();
}
