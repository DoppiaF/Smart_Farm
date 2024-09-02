package com.unife.project.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.unife.project.model.mo.VisitaVeterinaria;

public class VisitaVeterinariaDAOImpl implements VisitaVeterinariaDAO {
    
    private List<VisitaVeterinaria> visiteVeterinarie = null;

    //informazioni di connessione al database
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public VisitaVeterinariaDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(VisitaVeterinaria visitaVeterinaria) {
        String sql = "INSERT INTO visitaVeterinaria (data, diagnosi, identificativoAnimale, prossimaVisita, nomeVeterinario, cognomeVeterinario, curaPrescritta) VALUES (?, ?, ?, ?, ?, ? , ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, visitaVeterinaria.getData());
            ps.setString(2, visitaVeterinaria.getDiagnosi());
            ps.setInt(3, visitaVeterinaria.getIdentificativoAnimale());
            ps.setObject(4, visitaVeterinaria.getProssimaVisita());
            ps.setString(5, visitaVeterinaria.getNomeVeterinario());
            ps.setString(6, visitaVeterinaria.getCognomeVeterinario());
            ps.setString(7, visitaVeterinaria.getCuraPrescritta());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Una nuova visita veterinaria è stata inserita correttamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di una visita veterinaria");

        }
    }

    @Override
    public void update(VisitaVeterinaria visitaVeterinaria) {
        String sql = "UPDATE visitaVeterinaria SET diagnosi = ?, prossimaVisita = ?, nomeVeteriario = ?, cognomeVeterinario = ?, curaPrescritta = ? WHERE data = ? and identificativoAnimale = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, visitaVeterinaria.getDiagnosi());
            ps.setObject(2, visitaVeterinaria.getProssimaVisita());
            ps.setString(3, visitaVeterinaria.getNomeVeterinario());
            ps.setString(4, visitaVeterinaria.getCognomeVeterinario());
            ps.setString(5, visitaVeterinaria.getCuraPrescritta());
            ps.setObject(6, visitaVeterinaria.getData());
            ps.setInt(7, visitaVeterinaria.getIdentificativoAnimale());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La visita veterinaria è stata aggiornata correttamente!");
            }

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di una visita veterinaria");
        }
    }

    @Override
    public void delete(VisitaVeterinaria visitaVeterinaria) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM visitaVeterinaria WHERE data = ? and identificativoAnimale = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, visitaVeterinaria.getData());
            ps.setInt(2, visitaVeterinaria.getIdentificativoAnimale());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La visita veterinaria è stata eliminata correttamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di una visita veterinaria");
        }
    }

    @Override
    public VisitaVeterinaria findById(int id) {
        // TODO Auto-generated method stub
        System.out.println("Metodo non implementato. utilizzare findByDataAndIdentificativoAnimale");
        return null;
    }

    @Override
    public List<VisitaVeterinaria> findAll() {
        // TODO Auto-generated method stub

        visiteVeterinarie = new ArrayList<VisitaVeterinaria>();

        String sql = "SELECT * FROM visitaVeterinaria";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next() == false) {
                    System.out.println("Non ci sono visite veterinarie nel database");
                } else {
                    while(rs.next()){
                        VisitaVeterinaria visitaVeterinaria = new VisitaVeterinaria(
                            rs.getDate("data").toLocalDate(),
                            rs.getString("diagnosi"), 
                            rs.getInt("identificativoAnimale"),
                            rs.getDate("prossimaVisita").toLocalDate(),
                            rs.getString("nomeVeterinario"),
                            rs.getString("cognomeVeterinario"),
                            rs.getString("curaPrescritta"));

                        visiteVeterinarie.add(visitaVeterinaria);
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Errore nel recupero delle visite veterinarie");
        }

        return visiteVeterinarie;
    }
    
    @Override
    public List<VisitaVeterinaria> findByDataAndIdentificativoAnimale(LocalDate data, int identificativoAnimale) {
        visiteVeterinarie = new ArrayList<VisitaVeterinaria>();

        String sql = "SELECT * FROM visitaVeterinaria WHERE data = ? and identificativoAnimale = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, data);
            ps.setInt(2, identificativoAnimale);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next() == false) {
                    System.out.println("Non ci sono visite veterinarie nel database");
                } else {
                    while(rs.next()){
                        VisitaVeterinaria visitaVeterinaria = new VisitaVeterinaria(
                            rs.getDate("data").toLocalDate(),
                            rs.getString("diagnosi"), 
                            rs.getInt("identificativoAnimale"),
                            rs.getDate("prossimaVisita").toLocalDate(),
                            rs.getString("nomeVeterinario"),
                            rs.getString("cognomeVeterinario"),
                            rs.getString("curaPrescritta"));

                        visiteVeterinarie.add(visitaVeterinaria);
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Errore nel recupero delle visite veterinarie");
        }

        return visiteVeterinarie;
    }
}
