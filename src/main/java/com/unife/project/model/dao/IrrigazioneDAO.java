package com.unife.project.model.dao;

import com.unife.project.model.mo.Irrigazione;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class IrrigazioneDAO implements GenericDAO<Irrigazione> {
    private List<Irrigazione> irrigazioni = new ArrayList<>();
    private int idCounter = 1;

    //database connection
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public IrrigazioneDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Irrigazione irrigazione) {
        irrigazione.setId_impostazione(idCounter++);
        irrigazioni.add(irrigazione);
    }

    @Override
    public void update(Irrigazione irrigazione) {
        for (int i = 0; i < irrigazioni.size(); i++) {
            if (irrigazioni.get(i).getId_impostazione() == irrigazione.getId_impostazione()) {
                irrigazioni.set(i, irrigazione);
                return;
            }
        }
    }

    @Override
    public void delete(Irrigazione irrigazione) {
        irrigazioni.remove(irrigazione);
    }

    @Override
    public Irrigazione findById(int id) {
        for (Irrigazione irrigazione : irrigazioni) {
            if (irrigazione.getId_impostazione() == id) {
                return irrigazione;
            }
        }
        return null;
    }

    @Override
    public List<Irrigazione> findAll() {
        return new ArrayList<>(irrigazioni);
    }
}
