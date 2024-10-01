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
import org.springframework.cglib.core.Local;

import com.unife.project.model.mo.VisitaVeterinaria;


public class VisitaVeterinariaDAOImplTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private VisitaVeterinariaDAOImpl visitaVeterinariaDAO;
    
    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException {
        LocalDate dataVisita = LocalDate.of(2024, 07, 30);
        VisitaVeterinaria visitaVeterinaria = new VisitaVeterinaria();
        visitaVeterinaria.setData(dataVisita);
        visitaVeterinaria.setCognomeVeterinario("Zorzi");
        visitaVeterinaria.setCuraPrescritta("Solo crocchette");
        visitaVeterinaria.setDiagnosi("Mal di pancia");
        visitaVeterinaria.setNomeVeterinario("Oreste");
        visitaVeterinaria.setStatoAnimale("Spossato");
        visitaVeterinaria.setProgrammata(false);
        visitaVeterinaria.setIdentificativoAnimale(10);
        
        when(preparedStatement.executeUpdate()).thenReturn(1);

        visitaVeterinariaDAO.save(visitaVeterinaria);

        verify(connection, times(1)).prepareStatement("INSERT INTO visita_veterinaria (data, diagnosi, identificativo_animale, nome_veterinario, cognome_veterinario, cura_prescritta, stato_animale, programmata) VALUES (?, ?, ?, ?, ?, ? , ?, ?)");

        verify(preparedStatement, times(1)).setDate(1, dataVisita).toLocalDate();
        verify(preparedStatement, times(1));
        verify(preparedStatement, times(1));
        verify(preparedStatement, times(1));
        verify(preparedStatement, times(1));
        verify(preparedStatement, times(1));
        verify(preparedStatement, times(1));
        verify(preparedStatement, times(1));


    }
    
}
