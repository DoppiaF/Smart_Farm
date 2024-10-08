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
        String sql = "INSERT INTO visita_veterinaria (data, diagnosi, identificativo_animale, nome_veterinario, cognome_veterinario, cura_prescritta, stato_animale, programmata) VALUES (?, ?, ?, ?, ?, ? , ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, visitaVeterinaria.getData());
            ps.setString(2, visitaVeterinaria.getDiagnosi());
            ps.setInt(3, visitaVeterinaria.getIdentificativoAnimale());
            ps.setString(4, visitaVeterinaria.getNomeVeterinario());
            ps.setString(5, visitaVeterinaria.getCognomeVeterinario());
            ps.setString(6, visitaVeterinaria.getCuraPrescritta());
            ps.setString(7, visitaVeterinaria.getStatoAnimale());
            ps.setBoolean(8, visitaVeterinaria.getProgrammata());

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
        String sql = "UPDATE visita_veterinaria SET diagnosi = ?, nome_veterinario = ?, cognome_veterinario = ?, cura_prescritta = ?, stato_animale = ?, programmata = ? WHERE data = ? and identificativo_animale = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, visitaVeterinaria.getDiagnosi());
            ps.setString(2, visitaVeterinaria.getNomeVeterinario());
            ps.setString(3, visitaVeterinaria.getCognomeVeterinario());
            ps.setString(4, visitaVeterinaria.getCuraPrescritta());
            ps.setString(5, visitaVeterinaria.getStatoAnimale());
            ps.setBoolean(6, visitaVeterinaria.getProgrammata());
            ps.setObject(7, visitaVeterinaria.getData());
            ps.setInt(8, visitaVeterinaria.getIdentificativoAnimale());

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
        String sql = "DELETE FROM visita_veterinaria WHERE data = ? and identificativo_animale = ?";
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
        System.out.println("Metodo non implementato. utilizzare findByDataAndIdentificativoAnimale()");
        return null;
    }

    @Override
    public List<VisitaVeterinaria> findAll() {
        visiteVeterinarie = new ArrayList<VisitaVeterinaria>();

        String sql = "SELECT * FROM visita_veterinaria";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                if(!rs.isBeforeFirst()) {
                    System.out.println("Non ci sono visite veterinarie nel database");
                } else {
                    while(rs.next()){
                        VisitaVeterinaria visitaVeterinaria = new VisitaVeterinaria(
                            rs.getDate("data").toLocalDate(),
                            rs.getString("diagnosi"), 
                            rs.getInt("identificativo_animale"),
                            rs.getString("nome_veterinario"),
                            rs.getString("cognome_veterinario"),
                            rs.getString("cura_prescritta"),
                            rs.getString("stato_animale"),
                            rs.getBoolean("programmata"));  

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

        String sql = "SELECT * FROM visita_veterinaria WHERE data = ? and identificativo_animale = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, data);
            ps.setInt(2, identificativoAnimale);

            try (ResultSet rs = ps.executeQuery()) {
                if(!rs.isBeforeFirst()) {
                    System.out.println("Non ci sono visite veterinarie nel database");
                } else {
                    while(rs.next()){
                        VisitaVeterinaria visitaVeterinaria = new VisitaVeterinaria(
                            rs.getDate("data").toLocalDate(),
                            rs.getString("diagnosi"), 
                            rs.getInt("identificativo_animale"),
                            rs.getString("nome_veterinario"),
                            rs.getString("cognome_veterinario"),
                            rs.getString("cura_prescritta"),
                            rs.getString("stato_animale"),
                            rs.getBoolean("programmata"));

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
    public List<VisitaVeterinaria> findByIdAnimale(int idAnimale) {
        if(idAnimale <= 0) {
            throw new IllegalArgumentException("L'id dell'animale non è valido: "+ idAnimale);
        }
        visiteVeterinarie = new ArrayList<VisitaVeterinaria>();

        String sql = "SELECT * FROM visita_veterinaria WHERE identificativo_animale = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idAnimale);

            try (ResultSet rs = ps.executeQuery()) {
                if(!rs.isBeforeFirst()) {
                    System.out.println("Non ci sono visite veterinarie nel database");
                    return null;
                } else {
                    while(rs.next()){
                        /*LocalDate prossimaProgrammata = null;
                        if (rs.getDate("prossima_programmata") != null) {
                            prossimaProgrammata = rs.getDate("prossima_programmata").toLocalDate();
                        }*/
                        VisitaVeterinaria visitaVeterinaria = new VisitaVeterinaria(
                            rs.getDate("data").toLocalDate(),
                            rs.getString("diagnosi"), 
                            rs.getInt("identificativo_animale"),
                            rs.getString("nome_veterinario"),
                            rs.getString("cognome_veterinario"),
                            rs.getString("cura_prescritta"),
                            rs.getString("stato_animale"),
                            rs.getBoolean("programmata"));

                        visiteVeterinarie.add(visitaVeterinaria);
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Errore nel recupero delle visite veterinarie");
        }
        if (visiteVeterinarie.isEmpty()) {
            System.out.println("Nessuna visita trovata per l'animale con ID: " + idAnimale);
        }
        return visiteVeterinarie;
    }


    @Override
    public Connection getConnection() {
        return this.connection;
    }
}
