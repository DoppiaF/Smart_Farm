package com.unife.project.model.dao;

import com.unife.project.model.mo.Animale;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimaleDAOImpl implements AnimaleDAO {
    private List<Animale> animali = new ArrayList<>();
    //private int idCounter = 1;  //non serve, l'id è generato dal database

    //database connection details
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public AnimaleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Animale animale) {
        String sql = "INSERT INTO animale (peso, razza, tipoAlimentazione, nomeStalla, data_nascita, data_ingresso, data_uscita, data_morte, data_vaccino) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, animale.getPeso());
            ps.setString(2, animale.getRazza());
            ps.setString(3, animale.getTipoAlimentazione());
            ps.setString(4, animale.getNomeStalla());
            ps.setObject(5, animale.getData_nascita());
            ps.setObject(6, animale.getData_ingresso());
            ps.setObject(7, animale.getData_uscita());
            ps.setObject(8, animale.getData_morte());
            ps.setObject(9, animale.getData_vaccino());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Un nuovo animale è stato inserito correttamente!");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di un animale");
        }
    }

    @Override
    public void update(Animale animale) {
        String sql ="UPDATE animale" +
                    "SET peso = ?, sesso = ?, razza = ?, tipoAlimentazione = ?, nomeStalla = ?, data_nascita = ?, data_ingresso = ?, data_uscita = ?, data_morte = ?, data_vaccino = ?" + 
                    "WHERE id_animale = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di un animale " + animale.getId_animale());
        }
    }

    @Override
    public void delete(Animale animale) {
        String sql = "DELETE FROM animale WHERE id_animale = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, animale.getId_animale());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("L'animale è stato eliminato correttamente!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di un animale " );
        }
    }

    @Override
    public Animale findById(int id) {
        String sql = "SELECT * FROM animale WHERE id_animale = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Animale animale = new Animale(rs.getInt("peso"),
                        rs.getBoolean("sesso"),
                        rs.getString("razza"),
                        rs.getString("tipoAlimentazione"),
                        rs.getString("nomeStalla"),
                        rs.getDate("data_nascita").toLocalDate(),
                        rs.getDate("data_ingresso").toLocalDate(),
                        rs.getDate("data_uscita").toLocalDate(),
                        rs.getDate("data_morte").toLocalDate(),
                        rs.getDate("data_vaccino").toLocalDate());
                    return animale;
                } else {
                    System.out.println("Animale non trovato");
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca di un animale " + id);
        }
        return null;
    }

    @Override
    public List<Animale> findAll() {
        return animali;
        //da implementare
    }
}
