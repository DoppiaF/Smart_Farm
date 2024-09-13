package com.unife.project.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import com.unife.project.model.mo.Zona;

public class ZonaDAOImpl implements ZonaDAO {

    private List<Zona> zone = null;

    //informazioni database connection
    private Connection connection;

    //costruttore, utilizzato da DAOFactory
    public ZonaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Zona zona) {
        String sql = "INSERT INTO zona (coord_x, coord_y, portata_sensore, stato_generale_terreno, sensore_illuminazione, sensore_umidita, sensore_temperatura, sensore_PH, sensore_vento, id_piantagione) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, zona.getCoordX());
            ps.setInt(2, zona.getCoordY());
            ps.setInt(3, zona.getPortataSensore());
            ps.setString(4, zona.getStatoTerreno());
            ps.setFloat(5, zona.getSensoreIluminazione());
            ps.setFloat(6, zona.getSensoreUmidita());
            ps.setFloat(7, zona.getSensoreTemperatura());
            ps.setFloat(8, zona.getSensorePH());
            ps.setFloat(9, zona.getSensoreVento());
            ps.setInt(10, zona.getId_piantagione());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Una nuova zona è stata inserita correttamente!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'inserimento di una zona");
        }

    }

    @Override
    public void update(Zona zona) {
        String sql = "UPDATE zona SET portata_sensore = ?, stato_generale_terreno = ?, sensore_illuminazione = ?, sensore_umidita = ?, sensoreTemperatura = ?, sensore_PH = ?, sensore_vento = ? WHERE id_piantagione = ? and coord_x = ? and coord_x = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, zona.getPortataSensore());
            ps.setString(2, zona.getStatoTerreno());
            ps.setFloat(3, zona.getSensoreIluminazione());
            ps.setFloat(4, zona.getSensoreUmidita());
            ps.setFloat(5, zona.getSensoreTemperatura());
            ps.setFloat(6, zona.getSensorePH());
            ps.setFloat(7, zona.getSensoreVento());
            ps.setInt(8, zona.getId_piantagione());
            ps.setInt(9, zona.getCoordX());
            ps.setInt(10, zona.getCoordY());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La zona è stata aggiornata correttamente!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'aggiornamento di una zona");
        }
    }

    @Override
    public void delete(Zona zona) {
        String sql = "DELETE FROM zona WHERE coord_x = ? and coord_y = ? and id_piantagione = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, zona.getCoordX());
            ps.setInt(2, zona.getCoordY());
            ps.setInt(3, zona.getId_piantagione());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La zona è stata eliminata correttamente!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'eliminazione di una zona");
        }
    }

    @Override
    public Zona findById(int id) {
        // non implementabile

        System.out.println("Metodo non implementabile. utilizzare findByCoordAndIdPiantagione()");
        return null;
    }

    @Override
    public List<Zona> findAll() {
        zone = new ArrayList<Zona>();
        String sql = "SELECT * FROM zona";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                if(!rs.isBeforeFirst()){
                    System.out.println("Non ci sono zone nel database");
                } else {
                    while (rs.next()) {
                        Zona zona = new Zona(
                            rs.getInt("coord_x"),
                            rs.getInt("coord_y"),
                            rs.getInt("portata_sensore"),
                            rs.getString("stato_generale_terreno"),
                            rs.getFloat("sensore_illuminazione"),
                            rs.getFloat("sensore_umidita"),
                            rs.getFloat("sensore_temperatura"),
                            rs.getFloat("sensore_PH"),
                            rs.getFloat("sensore_vento"),
                            rs.getInt("id_piantagione")
                            );

                        zone.add(zona);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel recupero delle zone");
        }

        return zone;
    }


    @Override
    public Zona findByCoordAndPiantagione(int coordX, int coordY, int id_piantagione) {
        Zona zona = null;
        String sql = "SELECT * FROM zona WHERE coord_x = ? and coord_y = ? and id_piantagione = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, coordX);
            ps.setInt(2, coordY);
            ps.setInt(3, id_piantagione);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    zona = new Zona(
                        rs.getInt("coord_x"),
                        rs.getInt("coord_y"),
                        rs.getInt("portata_sensore"),
                        rs.getString("stato_generale_terreno"),
                        rs.getFloat("sensore_illuminazione"),
                        rs.getFloat("sensore_umidita"),
                        rs.getFloat("sensore_temperatura"),
                        rs.getFloat("sensore_PH"),
                        rs.getFloat("sensore_vento"),
                        rs.getInt("id_piantagione")
                        );
                } else {
                    System.out.println("Zona non trovata");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca di una zona");
        }

        return zona;
    }

    
    
    public List<Zona> findByPiantagione(int id_piantagione) {
        zone = new ArrayList<Zona>();
        Zona zona = null;
        String sql = "SELECT * FROM zona WHERE id_piantagione = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id_piantagione);

            try (ResultSet rs = ps.executeQuery()) {
                if(!rs.isBeforeFirst()){
                    System.out.println("Non sono state trovate zone per la piantagione selezionata");
                } else {
                    while (rs.next()) {
                        zona = new Zona(
                            rs.getInt("coord_x"),
                            rs.getInt("coord_y"),
                            rs.getInt("portata_sensore"),
                            rs.getString("stato_generale_terreno"),
                            rs.getFloat("sensore_illuminazione"),
                            rs.getFloat("sensore_umidita"),
                            rs.getFloat("sensore_temperatura"),
                            rs.getFloat("sensore_PH"),
                            rs.getFloat("sensore_vento"),
                            rs.getInt("id_piantagione")
                            );

                        zone.add(zona);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella ricerca delle zone della piantagione " + id_piantagione);
        }

        return zone;
    }   

}
