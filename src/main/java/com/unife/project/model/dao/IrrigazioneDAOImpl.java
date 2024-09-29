package com.unife.project.model.dao;

import com.unife.project.model.mo.Cisterna;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
//import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.unife.project.model.mo.Irrigazione;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class IrrigazioneDAOImpl implements IrrigazioneDAO {
    private List<Irrigazione> irrigazioni = null;  //lista da riempire coi dati raccolti dal database, passare poi a return

    //database connection
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public IrrigazioneDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Irrigazione irrigazione) {
        String sql = "INSERT INTO irrigazione (ora_inizio, durata, automatico, stato, litri_usati) VALUES (?,?,?,?,?)";    

        try{PreparedStatement ps = connection.prepareStatement(sql);
            ps.setTime(1, Time.valueOf(irrigazione.getOra_inizio()));
            ps.setInt(2, irrigazione.getDurata());
            ps.setBoolean(3, irrigazione.isAuto());
            ps.setString(4, irrigazione.getStato());
            ps.setInt(5, irrigazione.getLitri_usati());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Una nuova irrigazione è stata inserita correttamente!");
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di un'irrigazione");
        }
    }

    @Override
    public void update(Irrigazione irrigazione) {
        String sql = "UPDATE irrigazione " +
                    "SET ora_inizio = ?, durata = ?, automatico = ?, stato = ?, litri_usati = ? " +
                    "WHERE id_irrigazione = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setTime(1, Time.valueOf(irrigazione.getOra_inizio()));
            ps.setInt(2, irrigazione.getDurata());
            ps.setBoolean(3, irrigazione.isAuto());
            ps.setString(4, irrigazione.getStato());
            ps.setInt(5, irrigazione.getLitri_usati());
            ps.setInt(6, irrigazione.getId_irrigazione());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di un'irrigazione");
        }
    }

    @Override
    public void delete(Irrigazione irrigazione) {
        String sql = "DELETE FROM irrigazione WHERE id_irrigazione = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, irrigazione.getId_irrigazione());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0){
                System.out.println("L'irrigazione è stata eliminata correttamente");
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di un'irrigazione");
        }
    }

    @Override
    public Irrigazione findById(int id) {
        String sql = "SELECT * FROM irrigazione WHERE id_irrigazione = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Irrigazione irrigazione = new Irrigazione(
                        rs.getInt("id_irrigazione"),
                        rs.getTime("ora_inizio").toLocalTime(),
                        rs.getInt("durata"),
                        rs.getBoolean("automatico"),
                        rs.getString("stato"),
                        rs.getInt("litri_usati")
                    );

                    return irrigazione;
                } else {
                    System.out.println("Irrigazione non trovata");
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca di un'irrigazione " + id);
        }
        return null;
    }

    @Override
    public List<Irrigazione> findAll() {
        String sql = "SELECT * FROM irrigazione";
        irrigazioni = new ArrayList<Irrigazione>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono state trovate irrigazioni");
                else{
                    while (rs.next()){
                        Irrigazione irrigazione = new Irrigazione(
                            rs.getInt("id_irrigazione"),
                            rs.getTime("ora_inizio").toLocalTime(),
                            rs.getInt("durata"),
                            rs.getBoolean("automatico"),
                            rs.getString("stato"),
                            rs.getInt("litri_usati")
                        );
                        irrigazioni.add(irrigazione);  
                    }
                }
            }
        }
        catch (SQLException e){
                e.printStackTrace();
                System.out.println("Errore nel recupero delle informazioni di tutte le irrigazioni");
        }      
        return irrigazioni;
    }

    public List<Irrigazione> findAllWCisterna(){
        String sql = "SELECT i.*, c.* FROM irrigazione i join irrigazionecisterna ic join cisterna c on i.id_irrigazione=ic.id_irrigazione and c.id=ic.id_cisterna ORDER BY i.id_irrigazione";
        
        irrigazioni = new ArrayList<Irrigazione>();
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono state trovate irrigazioni");
                else{
                    while (rs.next()){
                        Irrigazione irrigazione = new Irrigazione(
                            rs.getInt("i.id_irrigazione"),
                            rs.getTime("i.ora_inizio").toLocalTime(),
                            rs.getInt("i.durata"),
                            rs.getBoolean("i.automatico"),
                            rs.getString("i.stato"),
                            rs.getInt("i.litri_usati"),
                            rs.getInt("c.id")
                        );

                        irrigazioni.add(irrigazione);
                        
                    }
                }
            }
        }
        catch (SQLException e){
                e.printStackTrace();
                System.out.println("Errore nel recupero delle informazioni di tutte le irrigazioni");
        }
        return irrigazioni;
    }

    public List<Integer> findAllIrrIds(){
        String sql = "SELECT id_irrigazione FROM irrigazione";
        List<Integer>idIrrigazioni = new ArrayList<Integer>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono state trovate irrigazioni");
                else{
                    while (rs.next()){
                        Integer idIrrigazione = rs.getInt("id_irrigazione");
                        idIrrigazioni.add(idIrrigazione);  
                    }
                }
            }
        }
        catch (SQLException e){
                e.printStackTrace();
                System.out.println("Errore nel recupero degli id delle irrigazioni esistenti");
        }   
        return idIrrigazioni;
    }
}
