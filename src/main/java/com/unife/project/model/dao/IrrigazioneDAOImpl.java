package com.unife.project.model.dao;

import com.unife.project.model.mo.Irrigazione;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class IrrigazioneDAOImpl implements IrrigazioneDAO {
    private List<Irrigazione> irrigazioni = new ArrayList<>();  //lista da riempire coi dati raccolti dal database, passare poi a return

    //database connection
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public IrrigazioneDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Irrigazione irrigazione) {
        
    }

    @Override
    public void update(Irrigazione irrigazione) {
        //da implementare
    }

    @Override
    public void delete(Irrigazione irrigazione) {
        //da implementare
    }

    @Override
    public Irrigazione findById(int id) {
        return null;
        //da implementare
    }

    @Override
    public List<Irrigazione> findAll() {
        return irrigazioni;
        //da implementare
    }
}
