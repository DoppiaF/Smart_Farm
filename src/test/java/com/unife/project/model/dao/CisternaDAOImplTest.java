package com.unife.project.model.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.unife.project.model.mo.Cisterna;

public class CisternaDAOImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private CisternaDAOImpl cisternaDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException, Exception {
        Cisterna cisterna = new Cisterna();
        cisterna.setCapacita(1000);
        cisterna.setQuantita(500);
        

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            cisternaDAO.save(cisterna);
        });

        // Verifica che il metodo save abbia chiamato executeUpdate
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("Una nuova cisterna è stata inserita correttamente!"));
    }

    @Test
    public void testUpdate() throws SQLException, Exception {
        Cisterna cisterna = new Cisterna();
        cisterna.setCapacita(1000);
        cisterna.setQuantita(500);

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            cisternaDAO.update(cisterna);
        });

        // Verifica che il metodo update abbia chiamato executeUpdate
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("Una nuova cisterna è stata inserita correttamente!"));
    }

    @Test
    public void testDelete() throws SQLException, Exception {
        Cisterna cisterna = new Cisterna();
        cisterna.setCapacita(1000);
        cisterna.setQuantita(500);
        cisterna.setId(1);

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            cisternaDAO.delete(cisterna);
        });

        // Verifica che il metodo delete abbia chiamato executeUpdate
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("La cisterna è stata eliminata correttamente!"));
    }

    @Test
    public void testFindAll() throws SQLException, Exception {
        List<Cisterna> cisterne = new ArrayList<>();
        Cisterna cisterna1 = new Cisterna();
        cisterna1.setCapacita(1000);
        cisterna1.setQuantita(500);
        cisterna1.setId(1000);
        Cisterna cisterna2 = new Cisterna();
        cisterna2.setCapacita(2000);
        cisterna2.setQuantita(1000);
        cisterna2.setId(2000);
        cisterne.add(cisterna1);
        cisterne.add(cisterna2);

        // Configura il mock per restituire i risultati del ResultSet

        when(resultSet.next()).thenReturn( true, true, false);
        when(resultSet.getInt("capacita")).thenReturn(cisterna1.getCapacita(), cisterna2.getCapacita());
        when(resultSet.getInt("quantita")).thenReturn(cisterna1.getQuantita(), cisterna2.getQuantita());

        // Configura il mock per restituire il ResultSet quando viene chiamato executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Esegui il metodo findAll
        List<Cisterna> result = cisternaDAO.findAll();

        // Verifica che il metodo findAll abbia chiamato executeQuery
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica che la lista restituita contenga le cisterne attese
        assertEquals(2, result.size());
        assertEquals(cisterna1.getCapacita(), result.get(0).getCapacita());
        assertEquals(cisterna2.getCapacita(), result.get(1).getCapacita());
        assertEquals(cisterna1.getQuantita(), result.get(0).getQuantita());
        assertEquals(cisterna2.getQuantita(), result.get(1).getQuantita());
    }
    

    @Test
    public void testFindById() throws SQLException, Exception {
        int cisternaId = 1;
        Cisterna cisterna = new Cisterna();
        cisterna.setId(cisternaId);
        cisterna.setCapacita(1000);

        // Configura il mock per restituire i risultati del ResultSet
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(cisterna.getId());
        when(resultSet.getInt("capacita")).thenReturn(cisterna.getCapacita());

        // Configura il mock per restituire il ResultSet quando viene chiamato executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Esegui il metodo findById
        Cisterna result = cisternaDAO.findById(cisternaId);

        // Verifica che il metodo findById abbia chiamato executeQuery
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica che la cisterna restituita sia quella attesa
        assertNotNull(result);
        assertEquals(cisterna.getId(), result.getId());
        assertEquals(cisterna.getCapacita(), result.getCapacita());
    }
}