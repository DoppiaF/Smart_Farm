package com.unife.project.model.dao;

import com.unife.project.model.mo.Animale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AnimaleDAOImplTest {

    private AnimaleDAOImpl animaleDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private Animale mockAnimale;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);

        mockResultSet = mock(ResultSet.class);
        mockAnimale = mock(Animale.class);
        animaleDAO = new AnimaleDAOImpl(mockConnection);

        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    public void testSave() throws SQLException {
        Animale animale = new Animale();
        animale.setSesso('M');
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
        verify(mockPreparedStatement, times(1)).setString(2, String.valueOf(animale.getSesso()));
        verify(mockPreparedStatement, times(1)).setString(3, animale.getRazza());
        verify(mockPreparedStatement, times(1)).setString(4, animale.getTipoAlimentazione());
        verify(mockPreparedStatement, times(1)).setString(5, animale.getNomeStalla());
        verify(mockPreparedStatement, times(1)).setObject(6, animale.getData_nascita());
        verify(mockPreparedStatement, times(1)).setObject(7, animale.getData_ingresso());
        verify(mockPreparedStatement, times(1)).setObject(8, animale.getData_uscita());
        verify(mockPreparedStatement, times(1)).setObject(9, animale.getData_morte());
        verify(mockPreparedStatement, times(1)).setObject(10, animale.getData_vaccino());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws Exception {
        // Arrange
        Animale animale = new Animale();
        animale.setId_animale(1);
        animale.setPeso(500);
        animale.setSesso('M');
        animale.setRazza("Frisona");
        animale.setTipoAlimentazione("Erba");
        animale.setNomeStalla("Stalla A");
        animale.setData_nascita(LocalDate.parse("2020-01-01"));
        animale.setData_ingresso(LocalDate.parse("2020-02-01"));
        animale.setData_uscita(LocalDate.parse("2021-01-01"));
        animale.setData_morte(LocalDate.parse("2021-02-01"));
        animale.setData_vaccino(LocalDate.parse("2020-03-01"));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Act
        animaleDAO.update(animale);

        // Assert
        verify(mockPreparedStatement, times(1)).setInt(1, animale.getPeso());
        verify(mockPreparedStatement, times(1)).setString(2, String.valueOf(animale.getSesso()));
        verify(mockPreparedStatement, times(1)).setString(3, animale.getRazza());
        verify(mockPreparedStatement, times(1)).setString(4, animale.getTipoAlimentazione());
        verify(mockPreparedStatement, times(1)).setString(5, animale.getNomeStalla());
        verify(mockPreparedStatement, times(1)).setObject(6, animale.getData_nascita());
        verify(mockPreparedStatement, times(1)).setObject(7, animale.getData_ingresso());
        verify(mockPreparedStatement, times(1)).setObject(8, animale.getData_uscita());
        verify(mockPreparedStatement, times(1)).setObject(9, animale.getData_morte());
        verify(mockPreparedStatement, times(1)).setObject(10, animale.getData_vaccino());
        verify(mockPreparedStatement, times(1)).setInt(11, animale.getId_animale());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDelete() throws SQLException {
        when(mockAnimale.getId_animale()).thenReturn(1);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        animaleDAO.delete(mockAnimale);

        verify(mockPreparedStatement, times(1)).setInt(1, 1);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testFindById() throws SQLException {
        int id = 1;
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("peso")).thenReturn(100);
        when(mockResultSet.getString("sesso")).thenReturn("M");
        when(mockResultSet.getString("razza")).thenReturn("Razza1");
        when(mockResultSet.getString("tipoAlimentazione")).thenReturn("Erba");
        when(mockResultSet.getString("nomeStalla")).thenReturn("Stalla1");
        when(mockResultSet.getDate("data_nascita")).thenReturn(java.sql.Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockResultSet.getDate("data_ingresso")).thenReturn(java.sql.Date.valueOf(LocalDate.of(2020, 2, 1)));
        when(mockResultSet.getDate("data_uscita")).thenReturn(java.sql.Date.valueOf(LocalDate.of(2020, 3, 1)));
        when(mockResultSet.getDate("data_morte")).thenReturn(java.sql.Date.valueOf(LocalDate.of(2020, 4, 1)));
        when(mockResultSet.getDate("data_vaccino")).thenReturn(java.sql.Date.valueOf(LocalDate.of(2020, 5, 1)));

        Animale animale = animaleDAO.findById(id);

        assertNotNull(animale);
        assertEquals(100, animale.getPeso());
        assertEquals('M', animale.getSesso());
        assertEquals("Razza1", animale.getRazza());
        assertEquals("Erba", animale.getTipoAlimentazione());
        assertEquals("Stalla1", animale.getNomeStalla());
        assertEquals(LocalDate.of(2020, 1, 1), animale.getData_nascita());
        assertEquals(LocalDate.of(2020, 2, 1), animale.getData_ingresso());
        assertEquals(LocalDate.of(2020, 3, 1), animale.getData_uscita());
        assertEquals(LocalDate.of(2020, 4, 1), animale.getData_morte());
        assertEquals(LocalDate.of(2020, 5, 1), animale.getData_vaccino());
    }
}