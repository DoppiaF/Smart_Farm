package com.unife.project.model.dao;

//import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.unife.project.model.mo.Irrigazione;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class IrrigazioneDAOImpl implements IrrigazioneDAO {
    private List<Irrigazione> irrigazioni = new ArrayList<>();  //lista da riempire coi dati raccolti dal database, passare poi a return

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
        //da implementare
    }

    @Override
    public void delete(Irrigazione irrigazione) {
        //da implementare
    }

    @Override
    public Irrigazione findById(int id) {
        return null;
        //da implementare
    }

    @Override
    public List<Irrigazione> findAll() {
        return irrigazioni;
        //da implementare
    }
}
