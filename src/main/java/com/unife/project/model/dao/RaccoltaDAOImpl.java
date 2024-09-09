package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Raccolta;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RaccoltaDAOImpl implements RaccoltaDAO{
    private List<Raccolta> raccolte = null;
    private Connection connection;

    public RaccoltaDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Raccolta raccolta) {
        String sql = "INSERT INTO raccolta (tipoPianta, quantita, data_raccolta, stato, macchinario, operatore, id_piantagione) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, raccolta.getTipoPianta());
            ps.setInt(2, raccolta.getQuantita());
            ps.setObject(3, raccolta.getDataRaccolta());
            ps.setString(4, raccolta.getStato());
            ps.setString(5, raccolta.getMacchinario());
            ps.setInt(6, raccolta.getOperatore());
            ps.setInt(7, raccolta.getId_piantagione());
            

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Una nuova raccolta è stata registrata correttamente!");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nella registrazione di una raccolta");
        }


    }

    @Override
    public void update(Raccolta raccolta) {
        String sql ="UPDATE raccolta" +
                    "SET tipoPianta = ?, quantita = ?, data_raccolta = ?, stato = ?, macchinario = ?, operatore = ?, id_piantagione = ?" + 
                    "WHERE id_raccolta = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, raccolta.getTipoPianta());
            ps.setInt(2, raccolta.getQuantita());
            ps.setObject(3, raccolta.getDataRaccolta());
            ps.setString(4, raccolta.getStato());
            ps.setString(5, raccolta.getMacchinario());
            ps.setInt(6, raccolta.getOperatore());
            ps.setInt(7, raccolta.getId_piantagione());
            ps.setInt(8, raccolta.getId_raccolta());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di una raccolta " + raccolta.getId_raccolta());
        }
    }

    @Override
    public void delete(Raccolta raccolta) {
        String sql = "DELETE FROM raccolta WHERE id_raccolta = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, raccolta.getId_raccolta());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La raccolta selezionata è stata eliminata correttamente!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di una raccolta " );
        }
    }

    @Override
    public Raccolta findById(int id) {
        String sql = "SELECT * FROM raccolta WHERE id_raccolta = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Raccolta raccolta = new Raccolta(
                        rs.getInt("id_raccolta"),
                        rs.getString("tipo_pianta"),
                        rs.getInt("quantita"),
                        rs.getDate("data_raccolta").toLocalDate(),
                        rs.getString("stato"),
                        rs.getInt("operatore"),
                        rs.getString("macchinario"),
                        rs.getInt("id_piantagione")
                    );

                    return raccolta;
                } else {
                    System.out.println("Raccolta non trovata");
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca di una raccolta " + id);
        }
        return null;
    }

    @Override
    public List<Raccolta> findAll() {
        String sql = "SELECT * FROM raccolta";
        raccolte = new ArrayList<Raccolta>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono state trovate raccolte");
                else{
                    while (rs.next()) {
                        Raccolta raccolta = new Raccolta(
                            rs.getInt("id_raccolta"),
                            rs.getString("tipo_pianta"),
                            rs.getInt("quantita"),
                            rs.getDate("data_raccolta").toLocalDate(),
                            rs.getString("stato"),
                            rs.getInt("operatore"),
                            rs.getString("macchinario"),
                            rs.getInt("id_piantagione")
                        );
                        raccolte.add(raccolta);
                    }
                } 
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni di tutte le raccolte");
        }
        return raccolte;
    }
    

}
