package com.unife.project.model.dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.unife.project.model.mo.Animale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AnimaleDAOImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @InjectMocks
    private AnimaleDAOImpl animaleDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException {
        Animale animale = new Animale();
        animale.setPeso(100);
        animale.setSesso('M');
        animale.setRazza("Bovino");
        animale.setTipoAlimentazione("grano");
        animale.setNomeStalla("stalla_A");
        animale.setData_nascita(LocalDate.of(2020, 1, 1));
        animale.setData_ingresso(LocalDate.of(2021, 1, 1));
        animale.setData_uscita(LocalDate.of(2022, 1, 1));
        animale.setData_morte(LocalDate.of(2023, 1, 1));
        animale.setData_vaccino(LocalDate.of(2024, 1, 1));

        animaleDAO.save(animale);

        verify(preparedStatement, times(1)).setInt(1, animale.getPeso());
        verify(preparedStatement, times(1)).setString(2, String.valueOf(animale.getSesso()));
        verify(preparedStatement, times(1)).setString(3, animale.getRazza());
        verify(preparedStatement, times(1)).setString(4, animale.getTipoAlimentazione());
        verify(preparedStatement, times(1)).setString(5, animale.getNomeStalla());
        verify(preparedStatement, times(1)).setObject(6, animale.getData_nascita());
        verify(preparedStatement, times(1)).setObject(7, animale.getData_ingresso());
        verify(preparedStatement, times(1)).setObject(8, animale.getData_uscita());
        verify(preparedStatement, times(1)).setObject(9, animale.getData_morte());
        verify(preparedStatement, times(1)).setObject(10, animale.getData_vaccino());
        verify(preparedStatement, times(1)).executeUpdate();
    }
}