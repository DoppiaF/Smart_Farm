package com.unife.project.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unife.project.model.mo.Listino;

public class ListinoDAOImpl implements ListinoDAO{
    private List<Listino> listini = null;

    //database connection information
    private Connection connection;
    
    public ListinoDAOImpl (Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Listino listino) {
        String sql = "INSERT INTO listino (tipo_prodotto, prezzo) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, listino.getTipo_prodotto());
            ps.setFloat(2, listino.getPrezzo());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Un nuovo prodotto è stato inserito correttamente!");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di un prodotto");
        }
    }

    @Override
    public void update(Listino listino) {
        String sql = "UPDATE listino SET prezzo = ? WHERE tipo_prodotto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setFloat(1, listino.getPrezzo());
            ps.setString(2, listino.getTipo_prodotto());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Il prezzo del prodotto è stato aggiornato correttamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento del prezzo di un prodotto");
        }
    }

    @Override
    public void delete(Listino listino) {
        String sql = "DELETE FROM listino WHERE tipo_prodotto = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, listino.getTipo_prodotto());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Il prodotto è stato eliminato correttamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di un prodotto");
        }
    }

    @Override
    public List<Listino> findAll() {
        listini = new ArrayList<Listino>();
        String sql = "SELECT * FROM listino";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try(ResultSet rs = ps.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("Non ci sono prodotti nel listino");
                } else {
                    while (rs.next()) {
                    Listino listino = new Listino(rs.getString("tipo_prodotto"), rs.getFloat("prezzo"));
                    listini.add(listino);
                    }
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di un prodotto");
        }
        return listini;
    }

    @Override
    public Listino findById(int id_listino){
        System.out.println("Metodo non implementato. utilizzare findByTipoProdotto(String tipo_prodotto)");
        return null;
    }

    @Override
    public List<Listino> findByTipoProdotto(String tipo_prodotto) {
        listini = new ArrayList<Listino>();
        String sql = "SELECT * FROM listino WHERE tipo_prodotto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tipo_prodotto);
            try(ResultSet rs = ps.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("Prodotto non trovato");
                } else {
                    while (rs.next()) {
                    Listino listino = new Listino(rs.getString("tipo_prodotto"), rs.getFloat("prezzo"));
                    listini.add(listino);
                    }
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nella ricerca di un prodotto");
        }
        return listini;
    }


    @Override
    public List<Listino> findAllPrezzoAggiornato() {
        List<Listino> listini = new ArrayList<>();
        String sql = "SELECT l1.tipo_prodotto, l1.prezzo " +
                     "FROM listino l1 " +
                     "INNER JOIN ( " +
                     "    SELECT tipo_prodotto, MAX(data_prezzamento) AS max_data " +
                     "    FROM listino " +
                     "    GROUP BY tipo_prodotto " +
                     ") l2 ON l1.tipo_prodotto = l2.tipo_prodotto AND l1.data_prezzamento = l2.max_data";
   
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try(ResultSet rs = ps.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("Non ci sono prodotti nel listino");
                } else {
                    while (rs.next()) {
                    Listino listino = new Listino(rs.getString("tipo_prodotto"), rs.getFloat("prezzo"));
                    listini.add(listino);
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Errore nella ricerca di un prodotto in listino");
        }
        return listini;
    }
}
