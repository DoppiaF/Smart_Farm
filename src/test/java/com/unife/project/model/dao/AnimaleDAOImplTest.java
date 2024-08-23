package com.unife.project.model.dao;

import com.unife.project.model.mo.Animale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AnimaleDAOImplTest {

    private AnimaleDAOImpl animaleDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        animaleDAO = new AnimaleDAOImpl(mockConnection);
    }

    @Test
    public void testSave() throws SQLException {
        Animale animale = new Animale();
        animale.setPeso(100);
        animale.setRazza("Frisona");
        animale.setTipoAlimentazione("Erba");
        animale.setNomeStalla("Stalla A");
        animale.setData_nascita(LocalDate.of(2020, 1, 1));
        animale.setData_ingresso(LocalDate.of(2020, 2, 1));
        animale.setData_uscita(null);
        animale.setData_morte(null);
        animale.setData_vaccino(LocalDate.of(2021, 1, 1));

        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setObject(anyInt(), any());
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        animaleDAO.save(animale);

        verify(mockPreparedStatement, times(1)).setInt(1, animale.getPeso());
        verify(mockPreparedStatement, times(1)).setString(2, animale.getRazza());
        verify(mockPreparedStatement, times(1)).setString(3, animale.getTipoAlimentazione());
        verify(mockPreparedStatement, times(1)).setString(4, animale.getNomeStalla());
        verify(mockPreparedStatement, times(1)).setObject(5, animale.getData_nascita());
        verify(mockPreparedStatement, times(1)).setObject(6, animale.getData_ingresso());
        verify(mockPreparedStatement, times(1)).setObject(7, animale.getData_uscita());
        verify(mockPreparedStatement, times(1)).setObject(8, animale.getData_morte());
        verify(mockPreparedStatement, times(1)).setObject(9, animale.getData_vaccino());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}