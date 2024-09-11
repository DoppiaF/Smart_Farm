package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Stalla;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StallaDAOImpl implements StallaDAO{
    private List<Stalla> stalle = null;

    //database connection details
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public StallaDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(Stalla stalla) {
        String sql = "INSERT INTO stalla (etichetta_stalla, capienza, razza, ora_pranzo, ora_cena) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stalla.getEtichettaStalla());
            ps.setInt(2, stalla.getCapienza());
            ps.setString(3, stalla.getRazza());
            ps.setObject(4, stalla.getOraPranzo());
            ps.setObject(5, stalla.getOraCena());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Una nuova stalla è stata registrata correttamente!");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nella registrazione di una stalla");
        }
    }

    @Override
    public void update(Stalla stalla) {
        String sql ="UPDATE stalla SET capienza = ?, razza = ?, ora_pranzo = ?, ora_cena = ? WHERE etichetta_stalla = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, stalla.getCapienza());
            ps.setString(2, stalla.getRazza());
            ps.setObject(3, stalla.getOraPranzo());
            ps.setObject(4, stalla.getOraCena());
            ps.setString(5, stalla.getEtichettaStalla());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\n\n\nErrore nell'aggiornamento della stalla: " + stalla.getEtichettaStalla() + "**************************");
        }
    }

    @Override
    public void delete(Stalla stalla) {
        String sql = "DELETE FROM stalla WHERE etichetta_stalla = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stalla.getEtichettaStalla());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La stalla è stata rimossa correttamente!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di una stalla" );
        }
    }

    @Override
    public Stalla findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Utilizza il metodo findByEtichetta al posto di findById per la stalla");
    }

    public Stalla findByEtichetta(String etichettaStalla){
        String sql = "SELECT * FROM stalla WHERE etichetta_stalla = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,etichettaStalla);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Stalla stalla = new Stalla(
                        etichettaStalla,
                        rs.getInt("capienza"),
                        rs.getString("razza"),
                        rs.getTime("ora_pranzo").toLocalTime(),
                        rs.getTime("ora_cena").toLocalTime()
                    );
                    return stalla;
                } else {
                    System.out.println("Stalla non trovata");
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca della stalla " + etichettaStalla);
        }
        return null;
    }

    @Override
    public List<Stalla> findAll() {
        
        String sql = "SELECT * FROM stalla";
        stalle = new ArrayList<Stalla>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono state trovate stalle");
                else{
                    while(rs.next()) {
                        Stalla stalla = new Stalla(
                            rs.getString("etichetta_stalla"),
                            rs.getInt("capienza"),
                            rs.getString("razza"),
                            rs.getTime("ora_pranzo").toLocalTime(),
                            rs.getTime("ora_cena").toLocalTime()
                        );
                        stalle.add(stalla);
                    }
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni di tutte le stalle ");
        }
        return stalle;
    }
    
    @Override
    public List<String> findAllEtichette(){
        String sql = "SELECT etichetta_stalla FROM stalla";
        List<String> etichette = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono state trovate stalle");
                else{
                    while(rs.next()) {
                        etichette.add(rs.getString("etichetta_stalla"));
                    }
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Errore nel recupero delle etichette delle stalle");
        }
        return etichette;
    }
}
