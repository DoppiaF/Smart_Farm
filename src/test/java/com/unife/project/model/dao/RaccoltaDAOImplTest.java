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

import com.unife.project.model.mo.Prodotto;
import com.unife.project.model.mo.Raccolta;

public class RaccoltaDAOImplTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private RaccoltaDAOImpl raccoltaDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException {
        Raccolta raccolta = new Raccolta();
        raccolta.setTipoPianta("bietola");
        raccolta.setQuantita(100);
        raccolta.setDataRaccolta(LocalDate.of(2023, 10, 1));
        raccolta.setStato("completata");
        raccolta.setMacchinario("falce");
        raccolta.setOperatore(1);
        raccolta.setId_piantagione(1);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        raccoltaDAO.save(raccolta);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("INSERT INTO raccolta (tipo_pianta, quantita, data_raccolta, stato, macchinario_usato, operatore, id_piantagione) VALUES (?, ?, ?, ?, ?, ?, ?)");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setString(1, "bietola");
        verify(preparedStatement, times(1)).setInt(2, 100);
        verify(preparedStatement, times(1)).setObject(3, LocalDate.of(2023, 10, 1));
        verify(preparedStatement, times(1)).setString(4, "completata");
        verify(preparedStatement, times(1)).setString(5, "falce");
        verify(preparedStatement, times(1)).setInt(6, 1);
        verify(preparedStatement, times(1)).setInt(7, 1);

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws SQLException {
        Raccolta raccolta = new Raccolta();
        raccolta.setId_raccolta(1);
        raccolta.setTipoPianta("bietola");
        raccolta.setQuantita(100);
        raccolta.setDataRaccolta(LocalDate.of(2023, 10, 1));
        raccolta.setStato("completata");
        raccolta.setMacchinario("falce");
        raccolta.setOperatore(1);
        raccolta.setId_piantagione(1);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        raccoltaDAO.update(raccolta);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("UPDATE raccolta SET tipo_pianta = ?, quantita = ?, data_raccolta = ?, stato = ?, macchinario_usato = ?, operatore = ?, id_piantagione = ? WHERE id_raccolta = ?");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setString(1, "bietola");
        verify(preparedStatement, times(1)).setInt(2, 100);
        verify(preparedStatement, times(1)).setObject(3, LocalDate.of(2023, 10, 1));
        verify(preparedStatement, times(1)).setString(4, "completata");
        verify(preparedStatement, times(1)).setString(5, "falce");
        verify(preparedStatement, times(1)).setInt(6, 1);
        verify(preparedStatement, times(1)).setInt(7, 1);
        verify(preparedStatement, times(1)).setInt(8, 1);

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }
}
