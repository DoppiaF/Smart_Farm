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
        /*String sql = "INSERT INTO irrigazioneCisterna (id_irrigazione, id_cisterna) VALUES (?,?)";
        try(Connection conn = DatabaseConnection.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, irrigazioneCisterna.getId_irrigazione());
            ps.setInt(2, irrigazioneCisterna.getId_cisterna());

            int rowsInserted = ps.executeUpdate();
            if(rowsInserted>0){
                System.out.println("elemento di IrrigazioneCisterna aggiunto correttamente");
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Elemento IrrigazioneCisterna non inserito");
        }
        */
        System.out.println("chiamato metodo irrigazioneCisternaDAOImpl.save(). Decommentare codice per implementarlo.");
    }

    @Override
    public void update(IrrigazioneCisterna irrigazioneCisterna) {
        // come metodo save, non serve implementare il metodo update per IrrigazioneCisterna
        System.out.println("IrrigazioneCisternaDAOImpl.update() chiamato. To be implemented");
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

        String sql = "SELECT * FROM irrigazioneCisterna";
        irrigazioniCisterne = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next() == false) {System.out.println("IrrigazioneCisterna non presenti in database");}
                else while(rs.next()){
                    IrrigazioneCisterna irrigazioneCisterna = new IrrigazioneCisterna(rs.getInt("id_irrigazione"), rs.getInt("id_cisterna"));
                    irrigazioniCisterne.add(irrigazioneCisterna);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Errore nel recupero di tutti i campi irrigazioneCisterna");
        }
        return irrigazioniCisterne;
    }

    @Override
    public List<IrrigazioneCisterna> findById_irrigazione(int id_irrigazione){

        String sql = "SELECT * FROM irrigazioneCisterna WHERE id_irrigazione = ?";
        irrigazioniCisterne = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id_irrigazione);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next() == false){ System.out.println("Non ci sono cisterne per il sistema di irrigazione con id " + id_irrigazione); }
                else{
                    while(rs.next()){
                        IrrigazioneCisterna irrigazioneCisterna = new IrrigazioneCisterna(rs.getInt("id_irrigazione"), rs.getInt("id_cisterna"));
                        irrigazioniCisterne.add(irrigazioneCisterna);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero di cisterne per irrigaizone con id " + id_irrigazione);

        }
        return irrigazioniCisterne;
    }

    @Override
    public List<IrrigazioneCisterna> findById_cisterna(int id_cisterna){

        String sql = "SELECT * FROM irrigazioneCisterna WHERE id_cisterna = ?";
        irrigazioniCisterne = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id_cisterna);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next() == false){ System.out.println("Non ci sono irrigazioni connesse a cisterna con id " + id_cisterna); }
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
