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

public class MagazzinoDAOImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private MagazzinoDAOImpl magazzinoDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException {
        Magazzino magazzino = new Magazzino();
        magazzino.setTipoMangime("Mais");
        magazzino.setQuantita(100);
        magazzino.setPrezzo_kg(1.0f);
        magazzino.setData(LocalDate.of(2023, 10, 1));

        // Configura il mock per restituire il numero di righe inserite
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Esegui il metodo save
        magazzinoDAO.save(magazzino);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("INSERT INTO magazzino_new (mangime, quantita, prezzo_kg, data) VALUES (?, ?, ?, ?)");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setString(1, magazzino.getTipoMangime());
        verify(preparedStatement, times(1)).setInt(2, magazzino.getQuantita());
        verify(preparedStatement, times(1)).setFloat(3, magazzino.getPrezzo_kg());
        verify(preparedStatement, times(1)).setDate(4, Date.valueOf(magazzino.getData()));

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws SQLException {
        Magazzino magazzino = new Magazzino();
        magazzino.setId(1);
        magazzino.setQuantita(200);
        magazzino.setPrezzo_kg(1.5f);
        magazzino.setTipoMangime("Mais");

        // Configura il mock per restituire il numero di righe aggiornate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Esegui il metodo update
        magazzinoDAO.update(magazzino);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("UPDATE magazzino_new SET quantita = ?, prezzo_kg = ?, mangime = ? WHERE id = ?");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setInt(1, magazzino.getQuantita());
        verify(preparedStatement, times(1)).setFloat(2, 1);
        verify(preparedStatement, times(1)).setString(3, magazzino.getTipoMangime());
        verify(preparedStatement, times(1)).setInt(4, magazzino.getId());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }
    
    @Test
    public void testDelete() throws SQLException {
        Magazzino magazzino = new Magazzino();
        magazzino.setId(1);
        magazzino.setQuantita(200);
        magazzino.setPrezzo_kg(1.5f);
        magazzino.setTipoMangime("Mais");

        // Configura il mock per restituire il numero di righe eliminate
        when(preparedStatement.executeUpdate()).thenReturn(1);


        // Esegui il metodo delete
        magazzinoDAO.delete(magazzino);

        
        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("DELETE FROM magazzino_new WHERE id = ?");

        // Verifica che il parametro sia stato impostato correttamente
        verify(preparedStatement, times(1)).setInt(1, magazzino.getId());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();

    }

    @Test
    public void testFindByTipoMangime() throws SQLException {
        String tipoMangime = "Mais";

        // Configura il mock per restituire un ResultSet con dati
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getFloat("prezzo_kg")).thenReturn(1.5f);
        when(resultSet.getInt("quantita")).thenReturn(100);
        when(resultSet.getDate("data")).thenReturn(java.sql.Date.valueOf(LocalDate.of(2023, 10, 1)));
        when(resultSet.getString("mangime")).thenReturn(tipoMangime);

        // Esegui il metodo findByTipoMangime
        Magazzino magazzino = magazzinoDAO.findByTipoMangime(tipoMangime);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("SELECT * FROM magazzino_new WHERE mangime = ?");

        // Verifica che il parametro sia stato impostato correttamente
        verify(preparedStatement, times(1)).setString(1, tipoMangime);

        // Verifica che il metodo executeQuery sia stato chiamato
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica che il metodo next del ResultSet sia stato chiamato
        verify(resultSet, times(1)).next();

        // Verifica i valori restituiti
        assertNotNull(magazzino);
        assertEquals(1, magazzino.getId());
        assertEquals(1.5f, magazzino.getPrezzo_kg());
        assertEquals(100, magazzino.getQuantita());
        assertEquals(LocalDate.of(2023, 10, 1), magazzino.getData());
        assertEquals(tipoMangime, magazzino.getTipoMangime());
    }

    @Test
    public void testFindAll() throws SQLException, Exception{
        List<Magazzino> magazzini = new ArrayList<>();
        
        Magazzino magazzino1 = new Magazzino();
        magazzino1.setId(1);
        magazzino1.setTipoMangime("Mais");
        magazzino1.setQuantita(100);
        magazzino1.setPrezzo_kg(1.0f);
        magazzino1.setData(LocalDate.of(2023, 10, 1));
        Magazzino magazzino2 = new Magazzino();
        magazzino2.setId(2);
        magazzino2.setTipoMangime("Orzo");
        magazzino2.setQuantita(200);
        magazzino2.setPrezzo_kg(1.5f);
        magazzino2.setData(LocalDate.of(2023, 10, 2));

        magazzini.add(magazzino1);
        magazzini.add(magazzino2);

        when(resultSet.isBeforeFirst()).thenReturn(true,true,false);
        when(resultSet.next()).thenReturn( true, true, false);
        when(resultSet.getInt("id")).thenReturn(magazzino1.getId(), magazzino2.getId());
        when(resultSet.getString("mangime")).thenReturn(magazzino1.getTipoMangime(), magazzino2.getTipoMangime());
        when(resultSet.getInt("quantita")).thenReturn(magazzino1.getQuantita(), magazzino2.getQuantita());
        when(resultSet.getFloat("prezzo_kg")).thenReturn(magazzino1.getPrezzo_kg(), magazzino2.getPrezzo_kg());
        when(resultSet.getDate("data")).thenReturn(java.sql.Date.valueOf(magazzino1.getData()), java.sql.Date.valueOf(magazzino2.getData()));

        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        List<Magazzino> result = magazzinoDAO.findAll();

        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(2, result.size());
        assertEquals(magazzino1.getId(), result.get(0).getId());
        assertEquals(magazzino2.getId(), result.get(1).getId());
        assertEquals(magazzino1.getTipoMangime(), result.get(0).getTipoMangime());
        assertEquals(magazzino2.getTipoMangime(), result.get(1).getTipoMangime());
        assertEquals(magazzino1.getQuantita(), result.get(0).getQuantita());
        assertEquals(magazzino2.getQuantita(), result.get(1).getQuantita());
        assertEquals(magazzino1.getPrezzo_kg(), result.get(0).getPrezzo_kg());
        assertEquals(magazzino2.getPrezzo_kg(), result.get(1).getPrezzo_kg());
        assertEquals(magazzino1.getData(), result.get(0).getData());
        assertEquals(magazzino2.getData(), result.get(1).getData());

    }
}