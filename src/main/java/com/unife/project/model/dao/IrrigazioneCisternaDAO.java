package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.IrrigazioneCisterna;

public interface IrrigazioneCisternaDAO extends GenericDAO<IrrigazioneCisterna>{
    //metodi specifici per irrigazioneCisterna

    List<IrrigazioneCisterna> findById_cisterna(int id_cisterna);
    IrrigazioneCisterna findById_irrigazione(int id_irrigazione);
}
