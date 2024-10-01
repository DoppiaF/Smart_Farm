package com.unife.project.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unife.project.model.mo.IrrigazioneCisterna;

public class IrrigazioneCisternaDAOImpl implements IrrigazioneCisternaDAO{
    
    private List<IrrigazioneCisterna> irrigazioniCisterne = null;

    //database connection details
    private Connection connection;
    
    public IrrigazioneCisternaDAOImpl (Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(IrrigazioneCisterna irrigazioneCisterna) {
        String sql = "INSERT INTO irrigazionecisterna (id_irrigazione, id_cisterna) VALUES (?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, irrigazioneCisterna.getId_irrigazione());
            ps.setInt(2, irrigazioneCisterna.getId_cisterna());

            int rowsInserted = ps.executeUpdate();
            if(rowsInserted>0){
                System.out.println("elemento di irrigazionecisterna aggiunto correttamente");
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Elemento irrigazionecisterna non inserito");
        }
    }

    @Override
    public void update(IrrigazioneCisterna irrigazioneCisterna) {
        String sql = "UPDATE irrigazionecisterna SET id_cisterna = ? WHERE id_irrigazione = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, irrigazioneCisterna.getId_cisterna());
            statement.setInt(2, irrigazioneCisterna.getId_irrigazione());
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated>0){
                System.out.println("elemento di irrigazionecisterna aggiornato correttamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Elemento irrigazionecisterna non aggiornato");
        }
    }

    @Override
    public void delete(IrrigazioneCisterna irrigazioneCisterna) {
        //metodo delete non implementabile.
        System.out.println("IrrigazioneCisternaDAOImpl.delete() chiamato. To be implemented");
    }

    @Override
    public IrrigazioneCisterna findById(int id_irrigazioneCisterna) {
        //non implementabile.
        System.out.println("IrrigazioneCisternaDAOIMpl.findById() chiamato. Utilizza invece findById_irrigazione o findById_cisterna");
        return null;

    }

    @Override
    public List<IrrigazioneCisterna> findAll() {

        String sql = "SELECT * FROM irrigazionecisterna";
        irrigazioniCisterne = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) {System.out.println("record di irrigazionecisterna non presenti nel database");}
                else while(rs.next()){
                    IrrigazioneCisterna irrigazioneCisterna = new IrrigazioneCisterna(rs.getInt("id_irrigazione"), rs.getInt("id_cisterna"));
                    irrigazioniCisterne.add(irrigazioneCisterna);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Errore nel recupero di tutti i campi irrigazionecisterna");
        }
        return irrigazioniCisterne;
    }

    @Override
    public IrrigazioneCisterna findById_irrigazione(int id_irrigazione){

        String sql = "SELECT * FROM irrigazionecisterna WHERE id_irrigazione = ?";
        IrrigazioneCisterna irrigazioneCisterna = null;

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id_irrigazione);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    irrigazioneCisterna = new IrrigazioneCisterna(rs.getInt("id_irrigazione"), rs.getInt("id_cisterna"));                } else{
                    System.out.println("Non ci sono cisterne per il sistema di irrigazione con id " + id_irrigazione);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero della cisterna per l' irrigazione con id " + id_irrigazione);

        }
        return irrigazioneCisterna;
    }

    @Override
    public List<IrrigazioneCisterna> findById_cisterna(int id_cisterna){

        String sql = "SELECT * FROM irrigazionecisterna WHERE id_cisterna = ?";
        irrigazioniCisterne = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id_cisterna);
            try(ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()){ System.out.println("Non ci sono irrigazioni connesse a cisterna con id " + id_cisterna); }
                else{
                    while(rs.next()){
                        IrrigazioneCisterna irrigazioneCisterna = new IrrigazioneCisterna(rs.getInt("id_irrigazione"), rs.getInt("id_cisterna"));
                        irrigazioniCisterne.add(irrigazioneCisterna);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero di cisterne per irrigaizone con id " + id_cisterna);

        }
        return irrigazioniCisterne;
    }
}
