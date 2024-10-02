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
        verify(connection, times(1)).prepareStatement("INSERT INTO irrigazione (id_irrigazione, ora_inizio, durata, automatico, stato, litri_usati) VALUES (?,?,?,?,?,?)");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setInt(1, irrigazione.getId_irrigazione());
        verify(preparedStatement, times(1)).setTime(2, Time.valueOf(irrigazione.getOra_inizio()));
        verify(preparedStatement, times(1)).setInt(3, irrigazione.getDurata());
        verify(preparedStatement, times(1)).setBoolean(4, irrigazione.isAuto());
        verify(preparedStatement, times(1)).setString(5, irrigazione.getStato());
        verify(preparedStatement, times(1)).setInt(6, irrigazione.getLitri_usati());
        
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

    @Test
    public void testFindAll () throws SQLException, Exception{
        List<Irrigazione> irrigazioni = new ArrayList<>();
        Irrigazione irrigazione1 = new Irrigazione(1, LocalTime.of(10,0), 30, true, "attivo", 100);
        Irrigazione irrigazione2 = new Irrigazione(2, LocalTime.of(11,0), 40, false, "disattivo", 200);

        irrigazioni.add(irrigazione1);
        irrigazioni.add(irrigazione2);

        // Configura il mock per restituire i risultati del ResultSet
        when(resultSet.isBeforeFirst()).thenReturn(true,true,false);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id_irrigazione")).thenReturn(irrigazione1.getId_irrigazione(), irrigazione2.getId_irrigazione());
        when(resultSet.getTime("ora_inizio")).thenReturn(Time.valueOf(irrigazione1.getOra_inizio()), Time.valueOf(irrigazione2.getOra_inizio()));
        when(resultSet.getInt("durata")).thenReturn(irrigazione1.getDurata(), irrigazione2.getDurata());
        when(resultSet.getBoolean("automatico")).thenReturn(irrigazione1.isAuto(), irrigazione2.isAuto());
        when(resultSet.getString("stato")).thenReturn(irrigazione1.getStato(), irrigazione2.getStato());
        when(resultSet.getInt("litri_usati")).thenReturn(irrigazione1.getLitri_usati(), irrigazione2.getLitri_usati());

        // Configura il mock per restituire il ResultSet quando viene chiamato executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Esegui il metodo findAll
        List<Irrigazione> result = irrigazioneDAO.findAll();

        // Verifica che il metodo findAll abbia chiamato executeQuery
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica che la lista restituita contenga le irrigazioni attese
        assertEquals(2, result.size());
        assertEquals(irrigazione2.getId_irrigazione(), result.get(1).getId_irrigazione());
        assertEquals(irrigazione1.getId_irrigazione(), result.get(0).getId_irrigazione());
        assertEquals(irrigazione2.getOra_inizio(), result.get(1).getOra_inizio());
        assertEquals(irrigazione1.getOra_inizio(), result.get(0).getOra_inizio());
        assertEquals(irrigazione2.getDurata(), result.get(1).getDurata());
        assertEquals(irrigazione1.getDurata(), result.get(0).getDurata());
        assertEquals(irrigazione2.isAuto(), result.get(1).isAuto());
        assertEquals(irrigazione1.isAuto(), result.get(0).isAuto());
        assertEquals(irrigazione2.getStato(), result.get(1).getStato());
        assertEquals(irrigazione1.getStato(), result.get(0).getStato());
        assertEquals(irrigazione2.getLitri_usati(), result.get(1).getLitri_usati());
        assertEquals(irrigazione1.getLitri_usati(), result.get(0).getLitri_usati());
    }
}