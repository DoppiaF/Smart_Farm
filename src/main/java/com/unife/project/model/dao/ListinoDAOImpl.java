package com.unife.project.model.dao;

import java.sql.Connection;
import java.util.List;

import com.unife.project.model.mo.Listino;

public class ListinoDAOImpl implements ListinoDAO{
    private List<Listino> listino = null;

    //database connection information
    private Connection connection;
    
    public ListinoDAOImpl (Connection connection){
        this.connection = connection;
    }

    @Override
    
}
