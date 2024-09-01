package com.unife.project.model.dao;

import com.unife.project.model.mo.ProdottoAnimale;
import com.unife.project.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoAnimaleDAOImpl implements ProdottoAnimaleDAO {

    private List<ProdottoAnimale> prodottiAnimali = new ArrayList<>();

    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public ProdottoAnimaleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(ProdottoAnimale prodottoAnimale) {
        /*String sql = "INSERT INTO prodotto_animale (id_animale, id_prodotto) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prodottoAnimale.getId_animale());
            stmt.setInt(2, prodottoAnimale.getId_prodotto());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Un nuovo prodotto animale è stato inserito correttamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di un prodotto_animale");
        }*/
        //-----------------------------------------------------------------------------------------
        System.out.println("ProdottoAnimaleDAOImpl.save() chiamato. Decommentare il metodo per abilitare l'inserimento.");
    }

    @Override
    public void update(ProdottoAnimale prodottoAnimale) {
        // Implementazione del metodo update
    }

    @Override
    public void delete(ProdottoAnimale prodottoAnimale) {
        // Implementazione del metodo delete
    }

    @Override
    public ProdottoAnimale findById(int id) {
        // Implementazione del metodo findById
        return null;
    }

    @Override
    public List<ProdottoAnimale> findAll() {
        // Implementazione del metodo findAll
        return null;
    }
}