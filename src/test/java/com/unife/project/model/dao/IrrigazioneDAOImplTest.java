package com.unife.project.model.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cglib.core.Local;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.unife.project.model.mo.Cisterna;
import com.unife.project.model.mo.Irrigazione;

public class IrrigazioneDAOImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private IrrigazioneDAOImpl irrigazioneDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException, Exception {
        Irrigazione irrigazione = new Irrigazione();
        irrigazione.setId_irrigazione(1);
        irrigazione.setOra_inizio(LocalTime.of(10,0));
        irrigazione.setDurata(30);
        irrigazione.setAuto(true);
        irrigazione.setStato("attivo");
        irrigazione.setLitri_usati(100);

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            irrigazioneDAO.save(irrigazione);
        });

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("INSERT INTO irrigazione (ora_inizio, durata, automatico, stato, litri_usati) VALUES (?,?,?,?,?)");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setTime(1, Time.valueOf(irrigazione.getOra_inizio()));
        verify(preparedStatement, times(1)).setInt(2, irrigazione.getDurata());
        verify(preparedStatement, times(1)).setBoolean(3, irrigazione.isAuto());
        verify(preparedStatement, times(1)).setString(4, irrigazione.getStato());
        verify(preparedStatement, times(1)).setInt(5, irrigazione.getLitri_usati());
        
        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("Una nuova irrigazione è stata inserita correttamente!"));
    }

    @Test
    public void testUpdate() throws SQLException, Exception {
        Irrigazione irrigazione = new Irrigazione();
        irrigazione.setId_irrigazione(1);
        irrigazione.setOra_inizio(LocalTime.of(10,0));
        irrigazione.setDurata(30);
        irrigazione.setAuto(true);
        irrigazione.setStato("attivo");
        irrigazione.setLitri_usati(100);

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            irrigazioneDAO.update(irrigazione);
        });

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("UPDATE irrigazione SET ora_inizio = ?, durata = ?, automatico = ?, stato = ?, litri_usati = ? WHERE id_irrigazione = ?");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setTime(1, Time.valueOf(irrigazione.getOra_inizio()));
        verify(preparedStatement, times(1)).setInt(2, irrigazione.getDurata());
        verify(preparedStatement, times(1)).setBoolean(3, irrigazione.isAuto());
        verify(preparedStatement, times(1)).setString(4, irrigazione.getStato());
        verify(preparedStatement, times(1)).setInt(5, irrigazione.getLitri_usati());
        verify(preparedStatement, times(1)).setInt(6, irrigazione.getId_irrigazione());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che non ci siano stati errori stampati nel terminale
        assertFalse(output.contains("Errore nell'aggiornamento di un'irrigazione"));
    }

    @Test
    public void testDelete() throws SQLException, Exception {
        Irrigazione irrigazione = new Irrigazione();
        irrigazione.setId_irrigazione(1);

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            irrigazioneDAO.delete(irrigazione);
        });

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("DELETE FROM irrigazione WHERE id_irrigazione = ?");

        // Verifica che il parametro sia stato impostato correttamente
        verify(preparedStatement, times(1)).setInt(1, irrigazione.getId_irrigazione());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("L'irrigazione è stata eliminata correttamente"));
    }

    @Test
    public void testFindById() throws SQLException {
        int id = 1;
        Irrigazione expectedIrrigazione = new Irrigazione(
            id,
            LocalTime.of(10, 0),
            30,
            true,
            "attivo",
            100
        );

        // Configura il mock per restituire un ResultSet con i dati attesi
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_irrigazione")).thenReturn(expectedIrrigazione.getId_irrigazione());
        when(resultSet.getTime("ora_inizio")).thenReturn(Time.valueOf(expectedIrrigazione.getOra_inizio()));
        when(resultSet.getInt("durata")).thenReturn(expectedIrrigazione.getDurata());
        when(resultSet.getBoolean("automatico")).thenReturn(expectedIrrigazione.isAuto());
        when(resultSet.getString("stato")).thenReturn(expectedIrrigazione.getStato());
        when(resultSet.getInt("litri_usati")).thenReturn(expectedIrrigazione.getLitri_usati());

        Irrigazione actualIrrigazione = irrigazioneDAO.findById(id);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("SELECT * FROM irrigazione WHERE id_irrigazione = ?");

        // Verifica che il parametro sia stato impostato correttamente
        verify(preparedStatement, times(1)).setInt(1, id);

        // Verifica che il metodo executeQuery sia stato chiamato
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica che i campi dell'oggetto restituito siano quelli attesi
        assertNotNull(actualIrrigazione);
        assertEquals(expectedIrrigazione.getId_irrigazione(), actualIrrigazione.getId_irrigazione());
        assertEquals(expectedIrrigazione.getOra_inizio(), actualIrrigazione.getOra_inizio());
        assertEquals(expectedIrrigazione.getDurata(), actualIrrigazione.getDurata());
        assertEquals(expectedIrrigazione.isAuto(), actualIrrigazione.isAuto());
        assertEquals(expectedIrrigazione.getStato(), actualIrrigazione.getStato());
        assertEquals(expectedIrrigazione.getLitri_usati(), actualIrrigazione.getLitri_usati());
    }
}