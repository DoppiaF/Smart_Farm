package com.unife.project.model.dao;

import com.unife.project.model.mo.Stalla;

public interface StallaDAO extends GenericDAO<Stalla>{
    public Stalla findByEtichetta(String etichettaStalla);
}
