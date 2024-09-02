package com.unife.project.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unife.project.model.mo.Utente;

public class UtenteDAOImpl implements UtenteDAO{

    private List<Utente> utenti = null;
    
    //informazioni di connessione al database
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public UtenteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Utente utente) {
        String sql = "INSERT INTO utente (username, password, email, data_nascita, ruolo_raccolta, ruolo_irrigazione, ruolo_pastore, ruolo_admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, utente.getUserName());
            ps.setString(2, utente.getPassword());
            ps.setString(3, utente.getEmail());
            ps.setObject(4, utente.getDataNascita());
            ps.setBoolean(5, utente.getRuolo_raccolta());
            ps.setBoolean(6, utente.getRuolo_irrigazione());
            ps.setBoolean(7, utente.getRuolo_pastore());
            ps.setBoolean(8, utente.getRuolo_admin());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Un nuovo utente è stato inserito correttamente!");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di un utente");
        }

    }

    @Override
    public void update(Utente utente) {
        String sql = "UPDATE utente" +
                     "SET username = ?, password = ?, email = ?, data_nascita = ?, ruolo_raccolta = ?, ruolo_irrigazione = ?, ruolo_pastore = ?, ruolo_admin = ?" + 
                     "WHERE id_utente = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, utente.getUserName());
            ps.setString(2, utente.getPassword());
            ps.setString(3, utente.getEmail());
            ps.setObject(4, utente.getDataNascita());
            ps.setBoolean(5, utente.getRuolo_raccolta());
            ps.setBoolean(6, utente.getRuolo_irrigazione());
            ps.setBoolean(7, utente.getRuolo_pastore());
            ps.setBoolean(8, utente.getRuolo_admin());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Un utente esistente è stato aggiornato correttamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di un utente");
        }
        
    }

    @Override
    public void delete(Utente utente) {
        String sql = "DELETE FROM utente WHERE id_utente = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, utente.getId());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Un utente è stato eliminato correttamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di un utente");
        }
        
    }

    @Override
    public Utente findById(int id) {
        // TODO Auto-generated method stub

        String sql = "SELECT * FROM utente WHERE id_utente = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) { System.out.println("Utente non trovato"); } 
            else {
                while(rs.next()){
                    Utente utente = new Utente();
                    utente.setId(rs.getInt("id_utente"));
                    utente.setUserName(rs.getString("username"));
                    utente.setPassword(rs.getString("password"));
                    utente.setEmail(rs.getString("email"));
                    utente.setCreateTime(rs.getTimestamp("create_time"));
                    utente.setDataNascita(rs.getDate("data_nascita").toLocalDate());
                    utente.setRuolo_raccolta(rs.getBoolean("ruolo_raccolta"));
                    utente.setRuolo_irrigazione(rs.getBoolean("ruolo_irrigazione"));
                    utente.setRuolo_pastore(rs.getBoolean("ruolo_pastore"));
                    utente.setRuolo_admin(rs.getBoolean("ruolo_admin"));
                    return utente;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Errore nella ricerca di un utente" + id);
        }

        return null;
    }

    @Override
    public List<Utente> findAll() {
        utenti = new ArrayList<Utente>();

        String sql = "SELECT * FROM utente";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {                
                if (rs.next() == false) { 
                    System.out.println("Nessun utente trovato"); }
                else{
                    while(rs.next()){
                        Utente utente = new Utente();
                        utente.setId(rs.getInt("id_utente"));
                        utente.setUserName(rs.getString("username"));
                        utente.setPassword(rs.getString("password"));
                        utente.setEmail(rs.getString("email"));
                        utente.setCreateTime(rs.getTimestamp("create_time"));
                        utente.setDataNascita(rs.getDate("data_nascita").toLocalDate());
                        utente.setRuolo_raccolta(rs.getBoolean("ruolo_raccolta"));
                        utente.setRuolo_irrigazione(rs.getBoolean("ruolo_irrigazione"));
                        utente.setRuolo_pastore(rs.getBoolean("ruolo_pastore"));
                        utente.setRuolo_admin(rs.getBoolean("ruolo_admin"));

                        utenti.add(utente);
                    }
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca di tutti gli utenti");
        }
        return utenti;
    }
}
