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
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testFindAll() throws SQLException, Exception{
        List<Piantagione> piantagioni = new ArrayList<>();
        
        Piantagione piantagione1 = new Piantagione();
        piantagione1.setId(1);
        piantagione1.setTipoPianta("Mais");
        piantagione1.setArea(100);
        piantagione1.setStato("Buono");
        piantagione1.setNumZone(5);
        piantagione1.setConcimazione(true);
        piantagione1.setId_irrigazione(2);
        piantagione1.setRaccolta(false);
        Piantagione piantagione2 = new Piantagione();
        piantagione2.setId(2);
        piantagione2.setTipoPianta("Pomodoro");
        piantagione2.setArea(200);
        piantagione2.setStato("Buono");
        piantagione2.setNumZone(10);
        piantagione2.setConcimazione(false);
        piantagione2.setId_irrigazione(3);
        piantagione2.setRaccolta(true);

        piantagioni.add(piantagione1);
        piantagioni.add(piantagione2);

        when(resultSet.isBeforeFirst()).thenReturn(true,true,false);
        when(resultSet.next()).thenReturn( true, true, false);
        when(resultSet.getInt("id")).thenReturn(piantagione1.getId(), piantagione2.getId());
        when(resultSet.getString("tipo_pianta")).thenReturn(piantagione1.getTipoPianta(), piantagione2.getTipoPianta());
        when(resultSet.getInt("area")).thenReturn(piantagione1.getArea(), piantagione2.getArea());
        when(resultSet.getString("stato")).thenReturn(piantagione1.getStato(), piantagione2.getStato());
        when(resultSet.getInt("num_zone")).thenReturn(piantagione1.getNumZone(), piantagione2.getNumZone());
        when(resultSet.getBoolean("concimazione")).thenReturn(piantagione1.isConcimazione(), piantagione2.isConcimazione());
        when(resultSet.getInt("id_irrigazione")).thenReturn(piantagione1.getId_irrigazione(), piantagione2.getId_irrigazione());
        when(resultSet.getBoolean("raccolta")).thenReturn(piantagione1.isRaccolta(), piantagione2.isRaccolta());

        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        List<Piantagione> result = piantagioneDAO.findAll();

        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(2, result.size());
        assertEquals(piantagione1.getId(), result.get(0).getId());
        assertEquals(piantagione2.getId(), result.get(1).getId());
        assertEquals(piantagione1.getTipoPianta(), result.get(0).getTipoPianta());
        assertEquals(piantagione2.getTipoPianta(), result.get(1).getTipoPianta());
        assertEquals(piantagione1.getArea(), result.get(0).getArea());
        assertEquals(piantagione2.getArea(), result.get(1).getArea());
        assertEquals(piantagione1.getStato(), result.get(0).getStato());
        assertEquals(piantagione2.getStato(), result.get(1).getStato());
        assertEquals(piantagione1.getNumZone(), result.get(0).getNumZone());
        assertEquals(piantagione2.getNumZone(), result.get(1).getNumZone());
        assertEquals(piantagione1.isConcimazione(), result.get(0).isConcimazione());
        assertEquals(piantagione2.isConcimazione(), result.get(1).isConcimazione());
        assertEquals(piantagione1.getId_irrigazione(), result.get(0).getId_irrigazione());
        assertEquals(piantagione2.getId_irrigazione(), result.get(1).getId_irrigazione());
        assertEquals(piantagione1.isRaccolta(), result.get(0).isRaccolta());
        assertEquals(piantagione2.isRaccolta(), result.get(1).isRaccolta());
        
    }
}
