package com.unife.project.model.dao;

import com.unife.project.model.mo.ProdottoAnimale;
import com.unife.project.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProdottoAnimaleDAO implements GenericDAO<ProdottoAnimale> {

    @Override
    public void save(ProdottoAnimale prodottoAnimale) {
        /*String sql = "INSERT INTO prodotto_animale (id, nome, tipo) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prodottoAnimale.getId());
            stmt.setString(2, prodottoAnimale.getNome());
            stmt.setString(3, prodottoAnimale.getTipo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
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