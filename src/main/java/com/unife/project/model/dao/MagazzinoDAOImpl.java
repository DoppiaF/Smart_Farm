package com.unife.project.model.dao;

import java.util.List;
import java.util.Map;

import com.unife.project.model.mo.Magazzino;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MagazzinoDAOImpl implements MagazzinoDAO{
    ArrayList<Magazzino> mangimi = null;
    
    private Connection connection;


    public MagazzinoDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Magazzino magazzino) {
        String sql = "INSERT INTO magazzino_new (mangime, quantita, prezzo_kg, data) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, magazzino.getTipoMangime());
            ps.setInt(2, magazzino.getQuantita());
            ps.setFloat(3, 1);
            ps.setDate(4, Date.valueOf(magazzino.getData()));
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
        String sql ="UPDATE magazzino_new " +
                    "SET quantita = ?, prezzo_kg = ?, mangime = ? " + 
                    "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, magazzino.getQuantita());
            ps.setFloat(2, 1);
            ps.setString(3, magazzino.getTipoMangime());
            ps.setInt(4, magazzino.getId());

            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di un tipo di mangime nel magazzino" + magazzino.getTipoMangime());
        }
    }

    @Override
    public void delete(Magazzino magazzino) {
        String sql = "DELETE FROM magazzino_new WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, magazzino.getId());
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
        String sql = "SELECT * FROM magazzino_new WHERE mangime = ?";
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,tipoMangime);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Magazzino magazzino = new Magazzino();
                    magazzino.setId(rs.getInt("id"));
                    magazzino.setPrezzo_kg(rs.getFloat("prezzo_kg"));
                    magazzino.setQuantita(rs.getInt("quantita"));
                    magazzino.setData(rs.getDate("data").toLocalDate());
                    magazzino.setTipoMangime(rs.getString("mangime"));
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
        String sql = "SELECT * FROM magazzino_new";
        mangimi = new ArrayList<Magazzino>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono stati trovati mangimi");
                else{
                    while (rs.next()){
                        Magazzino magazzino = new Magazzino();
                        magazzino.setId(rs.getInt("id"));
                        magazzino.setPrezzo_kg(rs.getFloat("prezzo_kg"));
                        magazzino.setQuantita(rs.getInt("quantita"));
                        magazzino.setData(rs.getDate("data").toLocalDate());
                        magazzino.setTipoMangime(rs.getString("mangime"));

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

    @Override
    public List<Magazzino> findAllUltimoAnno(){
        String sql = "SELECT * FROM magazzino_new WHERE data > DATE_SUB(NOW(), INTERVAL 1 YEAR)";
        mangimi = new ArrayList<Magazzino>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono stati trovati mangimi");
                else{
                    while (rs.next()){
                        Magazzino magazzino = new Magazzino();
                        magazzino.setId(rs.getInt("id"));
                        magazzino.setPrezzo_kg(rs.getFloat("prezzo_kg"));
                        magazzino.setQuantita(rs.getInt("quantita"));
                        magazzino.setData(rs.getDate("data").toLocalDate());
                        magazzino.setTipoMangime(rs.getString("mangime"));

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

    @Override
    public void eliminaMangime(){
        String queryAnimali = "SELECT tipo_alimentazione, COUNT(*) as numero_animali FROM animale GROUP BY tipo_alimentazione";
        String insertMagazzino = "INSERT INTO magazzino_new (mangime, quantita, data, prezzo_kg) VALUES (?, ?, ?, ?)";

        Map<String, Integer> consumoMangime = new HashMap<>();

        try (PreparedStatement stmtAnimali = connection.prepareStatement(queryAnimali);
             ResultSet rs = stmtAnimali.executeQuery()) {

            // Recupera e raggruppa gli animali per tipo di alimentazione
            while (rs.next()) {
                String tipoAlimentazione = rs.getString("tipo_alimentazione");
                int numeroAnimali = rs.getInt("numero_animali");
                int consumo = numeroAnimali * 2; // Ogni animale consuma 2 kg di mangime

                consumoMangime.put(tipoAlimentazione, consumo);
            }

            // Inserisci i dati nella tabella magazzino_new
            try (PreparedStatement stmtMagazzino = connection.prepareStatement(insertMagazzino)) {
                for (Map.Entry<String, Integer> entry : consumoMangime.entrySet()) {
                    String tipoMangime = entry.getKey();
                    int quantita = -entry.getValue(); // Quantità negativa
                    LocalDate dataOdierna = LocalDate.now();
                    int prezzoKg = 1; // Prezzo per kg impostato a 1

                    stmtMagazzino.setString(1, tipoMangime);
                    stmtMagazzino.setInt(2, quantita);
                    stmtMagazzino.setDate(3, java.sql.Date.valueOf(dataOdierna));
                    stmtMagazzino.setInt(4, prezzoKg);

                    stmtMagazzino.executeUpdate();
                }
            }

            System.out.println("Mangime generale eliminato manualmente con successo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
