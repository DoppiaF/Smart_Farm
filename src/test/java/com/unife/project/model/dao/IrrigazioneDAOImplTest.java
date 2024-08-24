package com.unife.project.model.dao;

import com.unife.project.model.mo.Irrigazione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class IrrigazioneDAOImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    private IrrigazioneDAO irrigazioneDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        irrigazioneDAO = new IrrigazioneDAOImpl(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException {
        Irrigazione irrigazione = new Irrigazione();
        
        LocalDate dataInizio = LocalDate.parse("2022-01-01");
        LocalTime oraInizio = LocalTime.parse("12:00:00");
        
        irrigazione.setData_inizio(dataInizio);
        irrigazione.setOra_inizio(oraInizio);
        irrigazione.setDurata(60);
        irrigazione.setAuto(true);
        irrigazione.setStato("In corso");
        irrigazione.setLitri_usati(100);

        when(preparedStatement.executeUpdate()).thenReturn(1);

        irrigazioneDAO.save(irrigazione);

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setDate(eq(1), any());
        verify(preparedStatement, times(1)).setTime(eq(2), any());
        verify(preparedStatement, times(1)).setInt(eq(3), eq(60));
        verify(preparedStatement, times(1)).setBoolean(eq(4), eq(true));
        verify(preparedStatement, times(1)).setString(eq(5), eq("In corso"));
        verify(preparedStatement, times(1)).setInt(eq(6), eq(100));
        verify(preparedStatement, times(1)).executeUpdate();
    }
}