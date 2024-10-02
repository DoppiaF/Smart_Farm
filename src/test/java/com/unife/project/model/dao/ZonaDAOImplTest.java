package com.unife.project.model.dao;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.unife.project.model.mo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.stefanbirkner.systemlambda.SystemLambda;

public class ZonaDAOImplTest {
    
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ZonaDAOImpl zonaDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException, Exception {
        Zona zona = new Zona();
        zona.setCoordX(0);
        zona.setCoordY(0);
        zona.setId_piantagione(1);
        zona.setPortataSensore(5);
        zona.setSensoreIluminazione(100);
        zona.setSensorePH(7);
        zona.setSensoreTemperatura(25);
        zona.setSensoreUmidita(45);
        zona.setSensoreVento(7);
        zona.setStatoTerreno("ok");

        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            zonaDAO.save(zona);
        });

        verify(preparedStatement, times(1)).executeUpdate();

        assertTrue(output.contains("Una nuova zona è stata inserita correttamente!"));

    }

    @Test
    public void testUpdate() throws SQLException, Exception {
        Zona zona = new Zona();
        zona.setCoordX(10);
        zona.setCoordY(2);
        zona.setId_piantagione(1);
        zona.setPortataSensore(5);
        zona.setSensoreIluminazione(101);
        zona.setSensorePH(7);
        zona.setSensoreTemperatura(26);
        zona.setSensoreUmidita(45);
        zona.setSensoreVento(7);
        zona.setStatoTerreno("ok");

        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            zonaDAO.update(zona);
        });

        verify(preparedStatement, times(1)).executeUpdate();

        assertTrue(output.contains("La zona è stata aggiornata correttamente!"));
        
    }

    @Test
    public void testDelete() throws SQLException, Exception {
        Zona zona = new Zona();
        zona.setCoordX(10);
        zona.setCoordY(3);
        zona.setId_piantagione(1);
        zona.setPortataSensore(5);
        zona.setSensoreIluminazione(101);
        zona.setSensorePH(7);
        zona.setSensoreTemperatura(26);
        zona.setSensoreUmidita(45);
        zona.setSensoreVento(7);
        zona.setStatoTerreno("ok");

        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            zonaDAO.delete(zona);
        });

        verify(preparedStatement, times(1)).executeUpdate();

        assertTrue(output.contains("La zona è stata eliminata correttamente!"));
    }
    
    @Test
    public void testFindByCoordAndPiantagione() throws SQLException, Exception {
        int coord_x = 0;
        int coord_y = 0;
        int id_piantagione = 1;

        Zona expectedZona = new Zona();

        expectedZona.setCoordX(coord_x);
        expectedZona.setCoordY(coord_y);
        expectedZona.setId_piantagione(id_piantagione);
        expectedZona.setPortataSensore(10);
        expectedZona.setSensoreIluminazione(98);
        expectedZona.setSensorePH(8);
        expectedZona.setSensoreTemperatura(20);
        expectedZona.setSensoreUmidita(50);
        expectedZona.setSensoreVento(10);
        expectedZona.setStatoTerreno("Ottimo");

            
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("coord_x")).thenReturn(expectedZona.getCoordX());
        when(resultSet.getInt("coord_y")).thenReturn(expectedZona.getCoordY());
        when(resultSet.getInt("id_piantagione")).thenReturn(expectedZona.getId_piantagione());
        when(resultSet.getInt("portata_sensore")).thenReturn(expectedZona.getPortataSensore());
        when(resultSet.getString("stato_generale_terreno")).thenReturn(expectedZona.getStatoTerreno());
        when(resultSet.getFloat("sensore_illuminazione")).thenReturn(expectedZona.getSensoreIluminazione());
        when(resultSet.getFloat("sensore_umidita")).thenReturn(expectedZona.getSensoreUmidita());
        when(resultSet.getFloat("sensore_temperatura")).thenReturn(expectedZona.getSensoreTemperatura());
        when(resultSet.getFloat("sensore_PH")).thenReturn(expectedZona.getSensorePH());
        when(resultSet.getFloat("sensore_vento")).thenReturn(expectedZona.getSensoreVento());
        
        Zona actualZona = zonaDAO.findByCoordAndPiantagione(coord_x, coord_y, id_piantagione);

        verify(connection, times(1)).prepareStatement("SELECT * FROM zona WHERE coord_x = ? and coord_y = ? and id_piantagione = ?");

        verify(preparedStatement, times(1)).setInt(1, coord_x);
        verify(preparedStatement, times(1)).setInt(2, coord_y);
        verify(preparedStatement, times(1)).setInt(3, id_piantagione);

        assertNotNull(actualZona);
        assertEquals(expectedZona.getCoordX(), actualZona.getCoordX());
        assertEquals(expectedZona.getCoordY(), actualZona.getCoordY());
        assertEquals(expectedZona.getId_piantagione(), actualZona.getId_piantagione());
        assertEquals(expectedZona.getPortataSensore(), actualZona.getPortataSensore());
        assertEquals(expectedZona.getSensoreIluminazione(), actualZona.getSensoreIluminazione());
        assertEquals(expectedZona.getSensorePH(), actualZona.getSensorePH());
        assertEquals(expectedZona.getSensoreTemperatura(), actualZona.getSensoreTemperatura());
        assertEquals(expectedZona.getSensoreUmidita(), actualZona.getSensoreUmidita());
        assertEquals(expectedZona.getSensoreVento(), actualZona.getSensoreVento());
        assertEquals(expectedZona.getStatoTerreno(), actualZona.getStatoTerreno());

    }

    @Test
    public void testFindAll() throws SQLException,Exception{
        List<Zona> zone = new ArrayList<>();

        Zona zona1 = new Zona();
        zona1.setCoordX(0);
        zona1.setCoordY(0);
        zona1.setId_piantagione(1);
        zona1.setPortataSensore(5);
        zona1.setSensoreIluminazione(100);
        zona1.setSensorePH(7);
        zona1.setSensoreTemperatura(25);
        zona1.setSensoreUmidita(45);
        zona1.setSensoreVento(7);
        zona1.setStatoTerreno("ok");

        Zona zona2 = new Zona();
        zona2.setCoordX(1);
        zona2.setCoordY(1);
        zona2.setId_piantagione(2);
        zona2.setPortataSensore(6);
        zona2.setSensoreIluminazione(101);
        zona2.setSensorePH(8);
        zona2.setSensoreTemperatura(26);
        zona2.setSensoreUmidita(46);
        zona2.setSensoreVento(8);
        zona2.setStatoTerreno("ok");

        zone.add(zona1);
        zone.add(zona2);

        when(resultSet.isBeforeFirst()).thenReturn(true, true, false);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("coord_x")).thenReturn(zona1.getCoordX(), zona2.getCoordX());
        when(resultSet.getInt("coord_y")).thenReturn(zona1.getCoordY(), zona2.getCoordY());
        when(resultSet.getInt("id_piantagione")).thenReturn(zona1.getId_piantagione(), zona2.getId_piantagione());
        when(resultSet.getInt("portata_sensore")).thenReturn(zona1.getPortataSensore(), zona2.getPortataSensore());
        when(resultSet.getFloat("sensore_illuminazione")).thenReturn(zona1.getSensoreIluminazione(), zona2.getSensoreIluminazione());
        when(resultSet.getFloat("sensore_PH")).thenReturn(zona1.getSensorePH(), zona2.getSensorePH());
        when(resultSet.getFloat("sensore_temperatura")).thenReturn(zona1.getSensoreTemperatura(), zona2.getSensoreTemperatura());
        when(resultSet.getFloat("sensore_umidita")).thenReturn(zona1.getSensoreUmidita(), zona2.getSensoreUmidita()); 
        when(resultSet.getFloat("sensore_vento")).thenReturn(zona1.getSensoreVento(), zona2.getSensoreVento());
        when(resultSet.getString("stato_generale_terreno")).thenReturn(zona1.getStatoTerreno(), zona2.getStatoTerreno());

        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        List<Zona> result = zonaDAO.findAll();

        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(2, result.size());
        assertEquals(zona1.getCoordX(), result.get(0).getCoordX());
        assertEquals(zona2.getCoordX(), result.get(1).getCoordX());
        assertEquals(zona1.getCoordY(), result.get(0).getCoordY());
        assertEquals(zona2.getCoordY(), result.get(1).getCoordY());
        assertEquals(zona1.getId_piantagione(), result.get(0).getId_piantagione());
        assertEquals(zona2.getId_piantagione(), result.get(1).getId_piantagione());
        assertEquals(zona1.getPortataSensore(), result.get(0).getPortataSensore());
        assertEquals(zona2.getPortataSensore(), result.get(1).getPortataSensore());
        assertEquals(zona1.getSensoreIluminazione(), result.get(0).getSensoreIluminazione());
        assertEquals(zona2.getSensoreIluminazione(), result.get(1).getSensoreIluminazione());
        assertEquals(zona1.getSensorePH(), result.get(0).getSensorePH());
        assertEquals(zona2.getSensorePH(), result.get(1).getSensorePH());
        assertEquals(zona1.getSensoreTemperatura(), result.get(0).getSensoreTemperatura());
        assertEquals(zona2.getSensoreTemperatura(), result.get(1).getSensoreTemperatura());
        assertEquals(zona1.getSensoreUmidita(), result.get(0).getSensoreUmidita());
        assertEquals(zona2.getSensoreUmidita(), result.get(1).getSensoreUmidita());
        assertEquals(zona1.getSensoreVento(), result.get(0).getSensoreVento());
        assertEquals(zona2.getSensoreVento(), result.get(1).getSensoreVento());
        assertEquals(zona1.getStatoTerreno(), result.get(0).getStatoTerreno());
        assertEquals(zona2.getStatoTerreno(), result.get(1).getStatoTerreno());
        
    }
}
