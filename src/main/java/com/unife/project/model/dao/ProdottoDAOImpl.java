package com.unife.project.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.cj.jdbc.PreparedStatementWrapper;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import com.unife.project.model.mo.Prodotto;



public class ProdottoDAOImpl implements ProdottoDAO{

    // informazioni su connessione al database
    private List<Prodotto> prodotti = null;

    private Connection connection;

    public ProdottoDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Prodotto prodotto) {
        String sql = "INSERT INTO prodotto (quantita, dataProduzione, dataScadenza, tipoProdotto)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,prodotto.getQuantita());
            ps.setObject(2, prodotto.getDataProduzione());
            ps.setObject(3, prodotto.getDataScadenza());
            ps.setString(4, prodotto.getTipoProdotto());
            int rowsInserted = ps.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Un nuovo prodotto è stato inserito correttamente");
            }         
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di un prodotto");
        }
    }

    @Override
    public void update(Prodotto prodotto) {
        String sql = "UPDATE prodotto" +
                    "SET quantita = ?, dataProduzione = ?, dataScadenza = ?, tipoProdotto = ?" +
                    "WHERE id_prodotto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,prodotto.getQuantita());
            ps.setObject(2, prodotto.getDataProduzione());
            ps.setObject(3, prodotto.getDataScadenza());
            ps.setString(4, prodotto.getTipoProdotto());
            ps.setInt(5, prodotto.getId_prodotto());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di un prodotto" + prodotto.getId_prodotto());
        }
    }

    @Override
    public void delete(Prodotto prodotto) {
        String sql = "DELETE FROM prodotto WHERE id_prodotto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, prodotto.getId_prodotto());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Il prodotto è stato eliminato correttamente!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di un prodotto " );
        }
    }

    @Override
    public Prodotto findById(int id) {
        String sql = "SELECT * FROM prodotto WHERE id_prodotto = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Prodotto prodotto = new Prodotto(
                        rs.getInt("quantita"),
                        rs.getString("tipoProdotto"),
                        rs.getTimestamp("dataProduzione").toLocalDateTime(),
                        rs.getTimestamp("dataScadenza").toLocalDateTime()
                    );

                    return prodotto;
                } else {
                    System.out.println("Animale non trovato");
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca di un prodotto " + id);
        }
        return null;
    }

    @Override
    public List<Prodotto> findAll() {
        String sql = "SELECT * FROM prodotto";
        prodotti = new ArrayList<Prodotto>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()==false) System.out.println("Non sono stati trovati prodotti");
                else{
                    while (rs.next()){
                        Prodotto prodotto = new Prodotto(
                            rs.getInt("quantita"),
                            rs.getString("tipoProdotto"),
                            rs.getTimestamp("dataProduzione").toLocalDateTime(),
                            rs.getTimestamp("dataScadenza").toLocalDateTime()
                        );

                        prodotti.add(prodotto);
                        
                    }
                }
            }
        }
        catch (SQLException e){
                e.printStackTrace();
                System.out.println("Errore nel recupero delle informazioni di tutti gli animali");
        }

        
        return prodotti;
    }
}
