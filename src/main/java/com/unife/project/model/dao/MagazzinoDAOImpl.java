package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Magazzino;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MagazzinoDAOImpl implements MagazzinoDAO{
    ArrayList<Magazzino> mangimi = null;
    
    private Connection connection;


    public MagazzinoDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Magazzino magazzino) {
        String sql = "INSERT INTO magazzino (tipo_mangime, quantita, prezzo_kg) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, magazzino.getTipoMangime());
            ps.setInt(2, magazzino.getQuantita());
            ps.setFloat(3, magazzino.getPrezzo_kg());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Un nuovo tipo di mangime è stato inserito correttamente nel magazzino!");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di un tipo di mangime nel magazzino");
        }
        
    }

    @Override
    public void update(Magazzino magazzino) {
        String sql ="UPDATE magazzino " +
                    "SET quantita = ?, prezzo_kg = ? " + 
                    "WHERE tipo_mangime = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, magazzino.getQuantita());
            ps.setFloat(2, magazzino.getPrezzo_kg());
            ps.setString(3, magazzino.getTipoMangime());

            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di un tipo di mangime nel magazzino" + magazzino.getTipoMangime());
        }
    }

    @Override
    public void delete(Magazzino magazzino) {
        String sql = "DELETE FROM magazzino WHERE tipo_mangime = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, magazzino.getTipoMangime());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Il mangime è stato eliminato correttamente dal magazzino!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di un mangime dal magazzino " );
        }
    }

    @Override
    public Magazzino findById(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'. usare findByTipoMangime");
    }

    public Magazzino findByTipoMangime(String tipoMangime){
        String sql = "SELECT * FROM magazzino WHERE tipo_mangime = ?";
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,tipoMangime);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Magazzino magazzino = new Magazzino();
                    magazzino.setPrezzo_kg(rs.getFloat("prezzo_kg"));
                    magazzino.setQuantita(rs.getInt("quantita"));
                    magazzino.setData(rs.getDate("data").toLocalDate());
                    magazzino.setTipoMangime(rs.getString("tipo_mangime"));
                    return magazzino;
                } else {
                    System.out.println("Il tipo di mangime richiesto non è stato trovato nel magazzino");
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca del mangime di tipo " + tipoMangime + " all'interno del magazzino");
        }
        return null;

    }

    @Override
    public List<Magazzino> findAll() {
        String sql = "SELECT * FROM magazzino";
        mangimi = new ArrayList<Magazzino>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono stati trovati mangimi");
                else{
                    while (rs.next()){
                        Magazzino magazzino = new Magazzino();
                        magazzino.setPrezzo_kg(rs.getFloat("prezzo_kg"));
                        magazzino.setQuantita(rs.getInt("quantita"));
                        magazzino.setData(rs.getDate("data").toLocalDate());
                        magazzino.setTipoMangime(rs.getString("tipo_mangime"));

                        mangimi.add(magazzino);
                        
                    }
                }
            }
        }
        catch (SQLException e){
                e.printStackTrace();
                System.out.println("Errore nel recupero delle informazioni dei mangimi presenti nel magazzino");
        }
        return mangimi;    
    }

    
    
}
