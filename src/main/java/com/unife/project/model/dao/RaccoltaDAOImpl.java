package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.ProdottoConPrezzo;
import com.unife.project.model.mo.Raccolta;
import com.unife.project.model.mo.RaccoltaPerAnno;
import com.unife.project.model.mo.RaccoltoPiantaConPrezzo;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RaccoltaDAOImpl implements RaccoltaDAO{
    private List<Raccolta> raccolte = null;
    private Connection connection;

    public RaccoltaDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Raccolta raccolta) {
        String sql = "INSERT INTO raccolta (tipo_pianta, quantita, data_raccolta, stato, macchinario_usato, operatore, id_piantagione) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, raccolta.getTipoPianta());
            ps.setInt(2, raccolta.getQuantita());
            ps.setObject(3, raccolta.getDataRaccolta());
            ps.setString(4, raccolta.getStato());
            ps.setString(5, raccolta.getMacchinario());
            ps.setInt(6, raccolta.getOperatore());
            ps.setInt(7, raccolta.getId_piantagione());
            

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Una nuova raccolta è stata registrata correttamente!");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nella registrazione di una raccolta");
        }
    }

    @Override
    public void update(Raccolta raccolta) {
        String sql ="UPDATE raccolta " +
                    "SET tipo_pianta = ?, quantita = ?, data_raccolta = ?, stato = ?, macchinario_usato = ?, operatore = ?, id_piantagione = ? " + 
                    "WHERE id_raccolta = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, raccolta.getTipoPianta());
            ps.setInt(2, raccolta.getQuantita());
            ps.setObject(3, raccolta.getDataRaccolta());
            ps.setString(4, raccolta.getStato());
            ps.setString(5, raccolta.getMacchinario());
            ps.setInt(6, raccolta.getOperatore());
            ps.setInt(7, raccolta.getId_piantagione());
            ps.setInt(8, raccolta.getId_raccolta());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di una raccolta " + raccolta.getId_raccolta());
        }
    }

    @Override
    public void delete(Raccolta raccolta) {
        String sql = "DELETE FROM raccolta WHERE id_raccolta = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, raccolta.getId_raccolta());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La raccolta selezionata è stata eliminata correttamente!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di una raccolta " );
        }
    }

    @Override
    public Raccolta findById(int id) {
        String sql = "SELECT * FROM raccolta WHERE id_raccolta = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Raccolta raccolta = new Raccolta(
                        rs.getInt("id_raccolta"),
                        rs.getString("tipo_pianta"),
                        rs.getInt("quantita"),
                        rs.getDate("data_raccolta").toLocalDate(),
                        rs.getString("stato"),
                        rs.getInt("operatore"),
                        rs.getString("macchinario_usato"),
                        rs.getInt("id_piantagione")
                    );

                    return raccolta;
                } else {
                    System.out.println("Raccolta non trovata");
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca di una raccolta " + id);
        }
        return null;
    }

    @Override
    public List<Raccolta> findAll() {
        String sql = "SELECT * FROM raccolta";
        raccolte = new ArrayList<Raccolta>();

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            
            try (ResultSet rs = ps.executeQuery()){
                if(!rs.isBeforeFirst()) System.out.println("Non sono state trovate raccolte");
                else{
                    while (rs.next()) {
                        Raccolta raccolta = new Raccolta(
                            rs.getInt("id_raccolta"),
                            rs.getString("tipo_pianta"),
                            rs.getInt("quantita"),
                            rs.getDate("data_raccolta").toLocalDate(),
                            rs.getString("stato"),
                            rs.getInt("operatore"),
                            rs.getString("macchinario_usato"),
                            rs.getInt("id_piantagione")
                        );
                        raccolte.add(raccolta);
                    }
                } 
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni di tutte le raccolte");
        }
        return raccolte;
    }
    

    @Override
    public List<RaccoltaPerAnno> findByPiantagione20Anni(int id_piantagione) {

       // String sql = "SELECT * FROM raccolta WHERE id_piantagione = 10 AND (YEAR(data_raccolta) > 20)";
        
       String sql = "SELECT YEAR(data_raccolta) AS anno, SUM(quantita) AS totale_quantita " +
                     "FROM raccolta " +
                     "WHERE id_piantagione = ? AND data_raccolta >= DATE_SUB(CURDATE(), INTERVAL 20 YEAR) " +
                     "GROUP BY anno " +
                     "ORDER BY anno";

       List<RaccoltaPerAnno> raccolte = new ArrayList<RaccoltaPerAnno>();
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id_piantagione);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    RaccoltaPerAnno raccolta = new RaccoltaPerAnno(
                        rs.getInt("anno"),
                        rs.getInt("totale_quantita")
                    );
                
                    raccolte.add(raccolta);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni delle raccolte di una piantagione " + id_piantagione);
        }
        return raccolte;
    }

     @Override
    public List<RaccoltoPiantaConPrezzo> findRaccoltaUltimoAnnoConPrezzo(){

        String query = "SELECT data_raccolta, quantita, tipo_pianta " +
               "FROM raccolta " +
               "WHERE data_raccolta >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)";
        
        List<RaccoltoPiantaConPrezzo> raccoltaConPrezzo = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)){
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.isBeforeFirst()) {
                    System.out.println("Non sono state trovati raccolte per l'ultimo anno");
                } else {
                    while (rs.next()) {
                        LocalDate dataRaccolta = rs.getDate("data_raccolta").toLocalDate();
                        Double quantita = rs.getDouble("quantita");
                        String tipoPianta = rs.getString("tipo_pianta");
                        Double prezzo ;

                        switch(tipoPianta.toLowerCase()){
                            case("grano"):
                                prezzo = 3.0;
                            case("frumento"):
                                prezzo = 3.0; 
                            case("mais"):
                                prezzo = 2.5; 
                            case("granoturco"):
                                prezzo = 2.5; 
                            case("cicoria"):
                                prezzo = 4.0; 
                            case("melanzana"):
                                prezzo = 4.0; 
                            case("grano saraceno"):
                                prezzo = 3.5; 
                            default:
                                prezzo = 4.0;
                            break;
                        }

                        RaccoltoPiantaConPrezzo raccolta = new RaccoltoPiantaConPrezzo(dataRaccolta, quantita, prezzo);
                        raccoltaConPrezzo.add(raccolta);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni dei prezzi di vendita del raccolto");
        }
    return raccoltaConPrezzo;
    }

    @Override
    public List<Raccolta> findByIdPiantagioneUltimoAnno(int id_piantagione){
        String sql = "SELECT * FROM raccolta WHERE id_piantagione = ? AND data_raccolta >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.isBeforeFirst()) {
                    System.out.println("Non sono state trovati raccolte per l'ultimo anno");
                } else {
                    while (rs.next()) {

                        Raccolta raccolta = new Raccolta(rs.getInt("id_raccolta"),
                        rs.getString("tipo_pianta"),
                        rs.getInt("quantita"),
                        rs.getDate("data_raccolta").toLocalDate(),
                        rs.getString("stato"),
                        rs.getInt("operatore"),
                        rs.getString("macchinario_usato"),
                        rs.getInt("id_piantagione")
                        );
                        raccolte.add(raccolta);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle informazioni dei prezzi di vendita del raccolto");
        }
        return raccolte;
    }

    @Override
    public List<Raccolta> findByIdPiantagioneAndMese(int id_piantagione, LocalDate meseAnno) {
        String sql = "SELECT * FROM raccolta WHERE id_piantagione = ? AND data_raccolta BETWEEN ? AND DATE_ADD(?, INTERVAL 1 MONTH)";
        List<Raccolta> raccolte = new ArrayList<>();
    
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id_piantagione);
            ps.setDate(2, java.sql.Date.valueOf(meseAnno));
            ps.setDate(3, java.sql.Date.valueOf(meseAnno));
            
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("Non sono state trovate raccolte per la piantagione " + id_piantagione + " nel mese " + meseAnno.getMonthValue() + " del " + meseAnno.getYear());
                } else {
                    while (rs.next()) {
                        Raccolta raccolta = new Raccolta(
                            rs.getInt("id_raccolta"),
                            rs.getString("tipo_pianta"),
                            rs.getInt("quantita"),
                            rs.getDate("data_raccolta").toLocalDate(),
                            rs.getString("stato"),
                            rs.getInt("operatore"),
                            rs.getString("macchinario_usato"),
                            rs.getInt("id_piantagione")
                        );
                        raccolte.add(raccolta);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca della raccolta della piantagione " + id_piantagione + " nel mese " + meseAnno);
        }
        return raccolte;
    }
    
}
