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

}
