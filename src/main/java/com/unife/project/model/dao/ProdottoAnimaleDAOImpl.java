package com.unife.project.model.dao;

import com.unife.project.model.mo.ProdottoAnimale;
import com.unife.project.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoAnimaleDAOImpl implements ProdottoAnimaleDAO {

    private List<ProdottoAnimale> prodottiAnimali = null;

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
        // come metodo save, non serve implementare il metodo update per ProdottoAnimale
        System.out.println("ProdottoAnimaleDAOImpl.update() chiamato. To be implemented");
    }

    @Override
    public void delete(ProdottoAnimale prodottoAnimale) {
        // Implementazione del metodo delete non serve. Ho aggiornamento a cascata
        System.out.println("ProdottoAnimaleDAOImpl.delete() chiamato. To be implemented");
    }

    @Override
    public ProdottoAnimale findById(int id_animale) {
        // Implementazione del metodo findById non serve.
        System.out.println("ProdottoAnimaleDAOImpl.findById() chiamato. To be implemented");
        return null;
    }

    @Override
    public List<ProdottoAnimale> findAll() {
        String sql = "SELECT * FROM prodotto_animale";
        prodottiAnimali = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            try( ResultSet rs = ps.executeQuery()){
                if(rs.next() == false){ System.out.println("Non ci sono prodotti animali"); }
                else{
                    while(rs.next()){
                        ProdottoAnimale prodottoAnimale = new ProdottoAnimale(rs.getInt("id_prodotto"), rs.getInt("id_animale"));
                        prodottiAnimali.add(prodottoAnimale);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero dei prodotti animali");
        }

        return prodottiAnimali;
    }

    @Override
    public List<ProdottoAnimale> findById_animale(int id_animale) {
        String sql = "SELECT * FROM prodotto_animale WHERE id_animale = ?";
        prodottiAnimali = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id_animale);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next() == false){ System.out.println("Non ci sono prodotti animali per l'animale con id " + id_animale); }
                else{
                    while(rs.next()){
                        ProdottoAnimale prodottoAnimale = new ProdottoAnimale(rs.getInt("id_prodotto"), rs.getInt("id_animale"));
                        prodottiAnimali.add(prodottoAnimale);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero dei prodotti animali per l'animale con id " + id_animale);

        }
    return prodottiAnimali;
    }

    @Override
    public List<ProdottoAnimale> findById_prodotto(int id_prodotto) {
        String sql = "SELECT * FROM prodotto_animale WHERE id_prodotto = ?";
        prodottiAnimali = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id_prodotto);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next() == false){ System.out.println("Non ci sono prodotti animali per il prodotto con id " + id_prodotto); }
                else{
                    while(rs.next()){
                        ProdottoAnimale prodottoAnimale = new ProdottoAnimale(rs.getInt("id_prodotto"), rs.getInt("id_animale"));
                        prodottiAnimali.add(prodottoAnimale);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero dei prodotti animali per il prodotto con id " + id_prodotto);
        }
        return prodottiAnimali;
    }
}