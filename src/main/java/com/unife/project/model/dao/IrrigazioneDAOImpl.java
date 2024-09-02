package com.unife.project.model.dao;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
//import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.unife.project.model.mo.Irrigazione;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
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
        String sql = "INSERT INTO irrigazione (data_inizio, ora_inizio, durata, auto, stato, litri_usati) VALUES (?,?,?,?,?,?)";    

        try{PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(irrigazione.getData_inizio()));
            ps.setTime(2, Time.valueOf(irrigazione.getOra_inizio()));
            ps.setInt(3, irrigazione.getDurata());
            ps.setBoolean(4, irrigazione.isAuto());
            ps.setString(5, irrigazione.getStato());
            ps.setInt(6, irrigazione.getLitri_usati());
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
        String sql = "UPDATE irrigazione" +
                    "SET data_inizio = ?, ora_inizio = ?, durata = ?, auto = ?, stato = ?, litri_usati = ?" +
                    "WHERE id_irrigazione = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDate(1, Date.valueOf(irrigazione.getData_inizio()));
            ps.setTime(2, Time.valueOf(irrigazione.getOra_inizio()));
            ps.setInt(3, irrigazione.getDurata());
            ps.setBoolean(4, irrigazione.isAuto());
            ps.setString(5, irrigazione.getStato());
            ps.setInt(6, irrigazione.getLitri_usati());
            ps.setInt(5, irrigazione.getId_irrigazione());
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
                        rs.getDate("data_inizio").toLocalDate(),
                        rs.getTime("ora_inizio").toLocalTime(),
                        rs.getInt("durata"),
                        rs.getBoolean("auto"),
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
                if(rs.next()==false) System.out.println("Non sono state trovate irrigazioni");
                else{
                    while (rs.next()){
                        Irrigazione irrigazione = new Irrigazione(
                            rs.getDate("data_inizio").toLocalDate(),
                            rs.getTime("ora_inizio").toLocalTime(),
                            rs.getInt("durata"),
                            rs.getBoolean("auto"),
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
}
