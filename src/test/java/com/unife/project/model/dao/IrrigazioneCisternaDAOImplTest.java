package com.unife.project.model.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cglib.core.Local;

import com.github.stefanbirkner.systemlambda.SystemLambda;

import com.unife.project.model.mo.IrrigazioneCisterna;

public class IrrigazioneCisternaDAOImplTest{
    
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private IrrigazioneCisternaDAOImpl irrigazioneCisternaDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException, Exception {
        IrrigazioneCisterna irrigazioneCisterna = new IrrigazioneCisterna();
        irrigazioneCisterna.setId_cisterna(1);
        irrigazioneCisterna.setId_irrigazione(1);
        
        when(preparedStatement.executeUpdate()).thenReturn(1);

        String output = SystemLambda.tapSystemOut(() -> {
            irrigazioneCisternaDAO.save(irrigazioneCisterna);
        });

        verify(connection, times(1)).prepareStatement("INSERT INTO irrigazionecisterna (id_irrigazione, id_cisterna) VALUES (?,?)");

        verify(preparedStatement, times(1)).setInt(1, irrigazioneCisterna.getId_irrigazione());
        verify(preparedStatement,times(1)).setInt(2, irrigazioneCisterna.getId_cisterna());

        verify(preparedStatement,times(1)).executeUpdate();
        assertTrue(output.contains("elemento di irrigazionecisterna aggiunto correttamente"));


    }
    //update

    @Test
    public void testUpdate() throws SQLException, Exception {
        IrrigazioneCisterna irrigazioneCisterna = new IrrigazioneCisterna();
        irrigazioneCisterna.setId_irrigazione(1);
        irrigazioneCisterna.setId_cisterna(2);

        when(preparedStatement.executeUpdate()).thenReturn(1);

        String output = SystemLambda.tapSystemOut(() -> {
            irrigazioneCisternaDAO.update(irrigazioneCisterna);
        });

        verify(connection, times(1)).prepareStatement("UPDATE irrigazionecisterna SET id_cisterna = ? WHERE id_irrigazione = ?");

        verify(preparedStatement, times(1)).setInt(1, irrigazioneCisterna.getId_cisterna());
        verify(preparedStatement, times(1)).setInt(2, irrigazioneCisterna.getId_irrigazione());

        verify(preparedStatement, times(1)).executeUpdate();

        assertFalse(output.contains("Elemento irrigazionecisterna non aggiornato"));


    }

    @Test
    public void testFindByIdIrrigazione() throws SQLException{
        int idIrrigazione = 1;
        IrrigazioneCisterna expectedIrrigazioneCisterna = new IrrigazioneCisterna(
            idIrrigazione,
            1
        );

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_irrigazione")).thenReturn(expectedIrrigazioneCisterna.getId_irrigazione());
        when(resultSet.getInt("id_cisterna")).thenReturn(expectedIrrigazioneCisterna.getId_cisterna());

        IrrigazioneCisterna actualIrrigazioneCisterna = irrigazioneCisternaDAO.findById_irrigazione(idIrrigazione);

        verify(connection, times(1)).prepareStatement("SELECT * FROM irrigazionecisterna WHERE id_irrigazione = ?");

        verify(preparedStatement, times(1)).setInt(1, idIrrigazione);

        verify(preparedStatement, times(1)).executeQuery();

        assertNotNull(actualIrrigazioneCisterna);
        assertEquals(expectedIrrigazioneCisterna.getId_irrigazione(), actualIrrigazioneCisterna.getId_irrigazione());
        assertEquals(expectedIrrigazioneCisterna.getId_cisterna(), actualIrrigazioneCisterna.getId_cisterna());
    }    

    @Test
    public void testFindAll() throws SQLException {
        // Configuro il mock per il PreparedStatement e il Connection
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        List<IrrigazioneCisterna> irrigazioniCisterne = new ArrayList<>();
        IrrigazioneCisterna irrigazione1 = new IrrigazioneCisterna(1, 101);
        IrrigazioneCisterna irrigazione2 = new IrrigazioneCisterna(2, 102);

        irrigazioniCisterne.add(irrigazione1);
        irrigazioniCisterne.add(irrigazione2);

        // Configura il mock per restituire i risultati del ResultSet
        when(resultSet.isBeforeFirst()).thenReturn(true,true,false);;
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id_irrigazione")).thenReturn(irrigazione1.getId_irrigazione(), irrigazione2.getId_irrigazione());
        when(resultSet.getInt("id_cisterna")).thenReturn(irrigazione1.getId_cisterna(), irrigazione2.getId_cisterna());

        // Chiama il metodo findAll
        List<IrrigazioneCisterna> result = irrigazioneCisternaDAO.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(irrigazione2.getId_cisterna(), result.get(1).getId_cisterna());
        assertEquals(irrigazione1.getId_cisterna(), result.get(0).getId_cisterna());
        assertEquals(irrigazione2.getId_irrigazione(), result.get(1).getId_irrigazione());
        assertEquals(irrigazione1.getId_irrigazione(), result.get(0).getId_irrigazione());
        
    }
}
