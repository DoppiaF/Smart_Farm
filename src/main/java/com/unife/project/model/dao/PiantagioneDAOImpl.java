package com.unife.project.model.dao;



import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unife.project.model.mo.Piantagione;

public class PiantagioneDAOImpl implements PiantagioneDAO{
    private List<Piantagione> piantagioni = null;
    //private int idCounter = 1;  //non serve, l'id è generato dal database

    //database connection details
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public PiantagioneDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(Piantagione piantagione) {
        String sql = "INSERT INTO piantagione (tipo_pianta, area, stato, num_zone, concimazione, raccolta) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, piantagione.getTipoPianta());
            ps.setInt(2, piantagione.getArea());
            ps.setString(3, piantagione.getStato());
            ps.setInt(4, piantagione.getNumZone());
            ps.setBoolean(5, piantagione.isConcimazione());
            ps.setBoolean(6, piantagione.isRaccolta());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Una nuova piantagione è stata registrata correttamente!");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nella registrazione di una piantagione");
        }
    }

    @Override
    public void update(Piantagione piantagione) {
        String sql ="UPDATE piantagione " +
                    "SET tipo_pianta = ?, area = ?, stato = ?, num_zone = ?, concimazione = ?, id_irrigazione = ?, raccolta = ? " + 
                    "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, piantagione.getTipoPianta());
            ps.setInt(2, piantagione.getArea());
            ps.setString(3, piantagione.getStato());
            ps.setInt(4, piantagione.getNumZone());
            ps.setBoolean(5, piantagione.isConcimazione());
            ps.setInt(6, piantagione.getId_irrigazione());
            ps.setBoolean(7, piantagione.isRaccolta());
            ps.setInt(8, piantagione.getId());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di una piantagione " + piantagione.getId());
        }
    }

    @Override
    public void delete(Piantagione piantagione) {
        String sql = "DELETE FROM piantagione WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, piantagione.getId());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La piantagione è stata eliminata correttamente!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di una piantagione " );
        }
    }

    @Override
    public Piantagione findById(int id) {
        String sql = "SELECT * FROM piantagione WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Piantagione piantagione = new Piantagione(
                    rs.getInt("id"),
                    rs.getString("tipo_pianta"),
                    rs.getInt("area"),
                    rs.getString("stato"),
                    rs.getInt("num_zone"),
                    rs.getBoolean("concimazione"),
                    rs.getInt("id_irrigazione"),
                    rs.getBoolean("raccolta")
                    );
                    return piantagione;
                } else {
                    System.out.println("Piantagione non trovata");
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca di una piantagione " + id);
        }
        return null;
    }

    @Override
    public List<Piantagione> findAll() {
        String sql = "SELECT * FROM piantagione";
        piantagioni = new ArrayList<Piantagione>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono state trovate piantagioni");
                else{
                    while (rs.next()) {
                        Piantagione piantagione = new Piantagione(
                        rs.getInt("id"),
                        rs.getString("tipo_pianta"),
                        rs.getInt("area"),
                        rs.getString("stato"),
                        rs.getInt("num_zone"),
                        rs.getBoolean("concimazione"),
                        rs.getInt("id_irrigazione"),
                        rs.getBoolean("raccolta"));
                        piantagioni.add(piantagione);
                    }
                } 
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni di tutte le piantagioni");
        }
        return piantagioni;
    }
    
}
