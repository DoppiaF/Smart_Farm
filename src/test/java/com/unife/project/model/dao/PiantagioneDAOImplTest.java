package com.unife.project.model.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.unife.project.model.mo.Magazzino;
import com.unife.project.model.mo.Piantagione;

public class PiantagioneDAOImplTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private PiantagioneDAOImpl piantagioneDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException {
        Piantagione piantagione = new Piantagione();
        piantagione.setId(1);
        piantagione.setTipoPianta("Pomodoro");
        piantagione.setArea(100);
        piantagione.setStato("Buono");
        piantagione.setNumZone(10);
        piantagione.setConcimazione(true);
        piantagione.setRaccolta(true);
        piantagione.setId_irrigazione(1);


        // Configura il mock per restituire il numero di righe inserite
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Esegui il metodo save
        piantagioneDAO.save(piantagione);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("INSERT INTO piantagione (tipo_pianta, area, stato, num_zone, concimazione, raccolta) VALUES (?, ?, ?, ?, ?, ?)");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setString(1,piantagione.getTipoPianta());
        verify(preparedStatement, times(1)).setInt(2, piantagione.getArea());
        verify(preparedStatement, times(1)).setString(3, piantagione.getStato());
        verify(preparedStatement, times(1)).setInt(4, piantagione.getNumZone());
        verify(preparedStatement, times(1)).setBoolean(5, piantagione.isConcimazione());
        verify(preparedStatement, times(1)).setBoolean(6, piantagione.isRaccolta());
        
        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws SQLException {
        Piantagione piantagione = new Piantagione();
        piantagione.setId(1);
        piantagione.setTipoPianta("Pomodoro");
        piantagione.setArea(100);
        piantagione.setStato("Buono");
        piantagione.setNumZone(10);
        piantagione.setConcimazione(true);
        piantagione.setRaccolta(true);
        piantagione.setId_irrigazione(1);

        // Configura il mock per restituire il numero di righe aggiornate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Esegui il metodo update
        piantagioneDAO.update(piantagione);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("UPDATE piantagione SET tipo_pianta = ?, area = ?, stato = ?, num_zone = ?, concimazione = ?, id_irrigazione = ?, raccolta = ? WHERE id = ?");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setString(1,piantagione.getTipoPianta());
        verify(preparedStatement, times(1)).setInt(2, piantagione.getArea());
        verify(preparedStatement, times(1)).setString(3, piantagione.getStato());
        verify(preparedStatement, times(1)).setInt(4, piantagione.getNumZone());
        verify(preparedStatement, times(1)).setBoolean(5, piantagione.isConcimazione());
        verify(preparedStatement, times(1)).setInt(6, piantagione.getId_irrigazione());
        verify(preparedStatement, times(1)).setBoolean(7, piantagione.isRaccolta());
        verify(preparedStatement, times(1)).setInt(8, piantagione.getId());

        
        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDelete() throws SQLException {
        Piantagione piantagione = new Piantagione();
        piantagione.setId(1);
        piantagione.setTipoPianta("Pomodoro");
        piantagione.setArea(100);
        piantagione.setStato("Buono");
        piantagione.setNumZone(10);
        piantagione.setConcimazione(true);
        piantagione.setRaccolta(true);
        piantagione.setId_irrigazione(1);

        // Configura il mock per restituire il numero di righe eliminate
        when(preparedStatement.executeUpdate()).thenReturn(1);


        // Esegui il metodo delete
        piantagioneDAO.delete(piantagione);

        
        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("DELETE FROM piantagione WHERE id = ?");

        // Verifica che il parametro sia stato impostato correttamente
        verify(preparedStatement, times(1)).setInt(1, piantagione.getId());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();

    }

    @Test
    public void testFindById() throws SQLException {
        int id = 1;

        // Configura il mock per restituire un ResultSet con dati
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("tipo_pianta")).thenReturn("Mais");
        when(resultSet.getInt("area")).thenReturn(100);
        when(resultSet.getString("stato")).thenReturn("Buono");
        when(resultSet.getInt("num_zone")).thenReturn(5);
        when(resultSet.getBoolean("concimazione")).thenReturn(true);
        when(resultSet.getInt("id_irrigazione")).thenReturn(2);
        when(resultSet.getBoolean("raccolta")).thenReturn(false);

        // Esegui il metodo findById
        Piantagione piantagione = piantagioneDAO.findById(id);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("SELECT * FROM piantagione WHERE id = ?");

        // Verifica che il parametro sia stato impostato correttamente
        verify(preparedStatement, times(1)).setInt(1, id);

        // Verifica che il metodo executeQuery sia stato chiamato
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica che il metodo next del ResultSet sia stato chiamato
        verify(resultSet, times(1)).next();

        // Verifica i valori restituiti
        assertNotNull(piantagione);
        assertEquals(id, piantagione.getId());
        assertEquals("Mais", piantagione.getTipoPianta());
        assertEquals(100, piantagione.getArea());
        assertEquals("Buono", piantagione.getStato());
        assertEquals(5, piantagione.getNumZone());
        assertTrue(piantagione.isConcimazione());
        assertEquals(2, piantagione.getId_irrigazione());
        assertFalse(piantagione.isRaccolta());
    }

}
