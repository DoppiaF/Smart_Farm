package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Cisterna;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CisternaDAOImpl implements CisternaDAO{
    private List<Cisterna> cisterne = new ArrayList<>();
    //private int idCounter = 1;  //non serve, l'id è generato dal database

    //database connection details
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public CisternaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Cisterna cisterna) {
        String sql = "INSERT INTO cisterna (capacita, quantita, tipoAlimentazione, nomeStalla, data_nascita, data_ingresso, data_uscita, data_morte, data_vaccino) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cisterna.getCapacita());
            ps.setInt(2, cisterna.getQuantita());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Una nuova cisterna è stata inserita correttamente!");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di una cisterna");
        }


    }

    @Override
    public void update(Cisterna cisterna) {
        String sql ="UPDATE cisterna" +
                    "SET capacita = ?, quantita = ?" + 
                    "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cisterna.getCapacita());
            ps.setInt(2, cisterna.getQuantita());
            ps.setInt(3, cisterna.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di una cisterna " + cisterna.getId());
        }
    }

    @Override
    public void delete(Cisterna cisterna) {
        String sql = "DELETE FROM cisterna WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cisterna.getId());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La cisterna è stata eliminata correttamente!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di una cisterna " );
        }
    }

    @Override
    public Cisterna findById(int id) {
        String sql = "SELECT * FROM cisterna WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Cisterna cisterna = new Cisterna(rs.getInt("capacita"),
                        rs.getInt("quantita"));
                        cisterna.setId(id);
                    return cisterna;
                } else {
                    System.out.println("Cisterna non trovata");
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca di una cisterna " + id);
        }
        return null;
    }

    @Override
    public List<Cisterna> findAll() {
        String sql = "SELECT * FROM cisterna";
        cisterne = new ArrayList<Cisterna>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono state trovate cisterne");
                else{
                    while (rs.next()){
                        Cisterna cisterna = new Cisterna(rs.getInt("capacita"),
                        rs.getInt("quantita"));

                        cisterne.add(cisterna);
                        
                    }
                }
            }
        }
        catch (SQLException e){
                e.printStackTrace();
                System.out.println("Errore nel recupero delle informazioni di tutte le cisterne");
        }
        return cisterne;    
    }
    
}
