package com.unife.project.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.cj.jdbc.PreparedStatementWrapper;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import com.unife.project.model.mo.Prodotto;
import com.unife.project.model.mo.ProdottoConPrezzo;



public class ProdottoDAOImpl implements ProdottoDAO{

    // informazioni su connessione al database
    private List<Prodotto> prodotti = null;

    private Connection connection;

    public ProdottoDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Prodotto prodotto) {
        String sql = "INSERT INTO prodotto (quantita, data_produzione, tipo_prodotto, specie, stalla) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,prodotto.getQuantita());
            ps.setDate(2, Date.valueOf(prodotto.getDataProduzione()));
            ps.setString(3, prodotto.getTipoProdotto());
            ps.setString(4, prodotto.getSpecie());
            ps.setString(5, prodotto.getStalla());

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
        String sql = "UPDATE prodotto " +
                    "SET quantita = ?, data_produzione = ?, tipo_prodotto = ?, specie = ?, stalla = ? " +
                    "WHERE id_prodotto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,prodotto.getQuantita());
            ps.setDate(2, Date.valueOf(prodotto.getDataProduzione()));
            ps.setString(3, prodotto.getTipoProdotto());
            ps.setString(4, prodotto.getSpecie());
            ps.setString(5, prodotto.getStalla());
            ps.setInt(6, prodotto.getId_prodotto());
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
    
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
    
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prodotto prodotto = new Prodotto();
                    prodotto.setId_prodotto(rs.getInt("id_prodotto"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    prodotto.setDataProduzione(rs.getDate("data_produzione").toLocalDate());
                    prodotto.setTipoProdotto(rs.getString("tipo_prodotto"));
                    prodotto.setSpecie(rs.getString("specie"));
                    prodotto.setStalla(rs.getString("stalla"));
    
                    return prodotto;
                } else {
                    System.out.println("prodotto non trovato");
                }
            }
        } catch (SQLException e) {
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
                if(!rs.isBeforeFirst()) System.out.println("Non sono stati trovati prodotti");
                else{
                    while (rs.next()){
                        Prodotto prodotto = new Prodotto();
                        prodotto.setId_prodotto(rs.getInt("id_prodotto"));
                        prodotto.setQuantita(rs.getInt("quantita"));
                        prodotto.setDataProduzione(rs.getDate("data_produzione").toLocalDate());
                        prodotto.setTipoProdotto(rs.getString("tipo_prodotto"));
                        prodotto.setSpecie(rs.getString("specie"));
                        prodotto.setStalla(rs.getString("stalla"));

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

    @Override
    public List<Prodotto> findProdottiUltimoAnno() {
        String sql = "SELECT * FROM prodotto WHERE data_produzione >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)";
        prodotti = new ArrayList<Prodotto>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono stati trovati prodotti");
                else{
                    while (rs.next()){
                        Prodotto prodotto = new Prodotto();
                        prodotto.setId_prodotto(rs.getInt("id_prodotto"));
                        prodotto.setQuantita(rs.getInt("quantita"));
                        prodotto.setDataProduzione(rs.getDate("data_produzione").toLocalDate());
                        prodotto.setTipoProdotto(rs.getString("tipo_prodotto"));
                        prodotto.setSpecie(rs.getString("specie"));
                        prodotto.setStalla(rs.getString("stalla"));

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


    
    @Override
    public List<Prodotto> findProdottiUltimoMese() {
        String sql = "SELECT * FROM prodotto WHERE data_produzione >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";
        List<Prodotto> prodotti = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("Non sono stati trovati prodotti");
                } else {
                    while (rs.next()) {
                        Prodotto prodotto = new Prodotto();
                        prodotto.setId_prodotto(rs.getInt("id_prodotto"));
                        prodotto.setQuantita(rs.getInt("quantita"));
                        prodotto.setDataProduzione(rs.getDate("data_produzione").toLocalDate());
                        prodotto.setTipoProdotto(rs.getString("tipo_prodotto"));
                        prodotto.setSpecie(rs.getString("specie"));
                        prodotto.setStalla(rs.getString("stalla"));

                        prodotti.add(prodotto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni di tutti gli animali");
        }
        return prodotti;
    }

    @Override
    public List<Prodotto> findProdottoUltimoAnnoPerRazza(String specie) {
        String sql = "SELECT * FROM prodotto WHERE data_produzione >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND specie = ?";
        List<Prodotto> prodotti = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, specie);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("Non sono stati trovati prodotti");
                } else {
                    while (rs.next()) {
                        Prodotto prodotto = new Prodotto();
                        prodotto.setId_prodotto(rs.getInt("id_prodotto"));
                        prodotto.setQuantita(rs.getInt("quantita"));
                        prodotto.setDataProduzione(rs.getDate("data_produzione").toLocalDate());
                        prodotto.setTipoProdotto(rs.getString("tipo_prodotto"));
                        prodotto.setSpecie(rs.getString("specie"));
                        prodotto.setStalla(rs.getString("stalla"));

                        prodotti.add(prodotto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni di tutti gli animali per razza");
        }
        return prodotti;
    }

    @Override
    public List<ProdottoConPrezzo> findProdottoUltimoAnnoConPrezzo(){

        String query = "SELECT p.data_produzione, p.quantita, l.prezzo " +
               "FROM prodotto p " +
               "JOIN ( " +
               "    SELECT tipo_prodotto, prezzo " +
               "    FROM listino l1 " +
               "    WHERE data_prezzamento = ( " +
               "        SELECT MAX(data_prezzamento) " +
               "        FROM listino l2 " +
               "        WHERE l1.tipo_prodotto = l2.tipo_prodotto " +
               "    ) " +
               ") l ON p.tipo_prodotto = l.tipo_prodotto " +
               "WHERE p.data_produzione >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)";
        
        List<ProdottoConPrezzo> prodottiConPrezzo = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)){
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.isBeforeFirst()) {
                    System.out.println("Non sono stati trovati prodotti");
                } else {
                    while (rs.next()) {
                        ProdottoConPrezzo prodotto = new ProdottoConPrezzo(rs.getDate("data_produzione").toLocalDate(), rs.getDouble("quantita"), rs.getDouble("prezzo"));
                        prodottiConPrezzo.add(prodotto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni prodotti per prezzo");
        }
    return prodottiConPrezzo;
    }
}
