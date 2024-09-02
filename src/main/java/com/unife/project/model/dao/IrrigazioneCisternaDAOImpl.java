package com.unife.project.model.dao;

import java.sql.Connection;
import java.util.List;

import com.unife.project.model.mo.IrrigazioneCisterna;

public class IrrigazioneCisternaDAOImpl implements IrrigazioneCisternaDAO{
    
    private List<IrrigazioneCisterna> irrigazioniCisterne = null;

    //database connection details
    private Connection connection;
    
    public IrrigazioneCisternaDAOImpl (Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(IrrigazioneCisterna irrigazioneCisterna) {

        
    }

    @Override
    public void update(IrrigazioneCisterna irrigazioneCisterna) {

    }

    @Override
    public void delete(IrrigazioneCisterna irrigazioneCisterna) {

    }

    @Override
    public IrrigazioneCisterna findById(int id_irrigazioneCisterna) {
        return null;

    }

    @Override
    public List<IrrigazioneCisterna> findAll() {
        return irrigazioniCisterne;

    }

}
