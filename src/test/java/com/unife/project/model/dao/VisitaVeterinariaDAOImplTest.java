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
import java.util.List;

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
        visitaVeterinaria.setDiagnosi("Mal di stomaco");
        visitaVeterinaria.setNomeVeterinario("Oreste");
        visitaVeterinaria.setStatoAnimale("Spossato");
        visitaVeterinaria.setProgrammata(false);
        visitaVeterinaria.setIdentificativoAnimale(10);
        
        when(preparedStatement.executeUpdate()).thenReturn(1);

        visitaVeterinariaDAO.save(visitaVeterinaria);

        verify(connection, times(1)).prepareStatement("INSERT INTO visita_veterinaria (data, diagnosi, identificativo_animale, nome_veterinario, cognome_veterinario, cura_prescritta, stato_animale, programmata) VALUES (?, ?, ?, ?, ?, ? , ?, ?)");

        verify(preparedStatement, times(1)).setObject(1, visitaVeterinaria.getData());
        verify(preparedStatement, times(1)).setString(2, visitaVeterinaria.getDiagnosi());
        verify(preparedStatement, times(1)).setInt(3, visitaVeterinaria.getIdentificativoAnimale());
        verify(preparedStatement, times(1)).setString(4, visitaVeterinaria.getNomeVeterinario());
        verify(preparedStatement, times(1)).setString(5, visitaVeterinaria.getCognomeVeterinario());
        verify(preparedStatement, times(1)).setString(6, visitaVeterinaria.getCuraPrescritta());
        verify(preparedStatement, times(1)).setString(7, visitaVeterinaria.getStatoAnimale());
        verify(preparedStatement, times(1)).setBoolean(8, visitaVeterinaria.getProgrammata());
        verify(preparedStatement, times(1)).executeUpdate();


    }

    @Test
    public void testUpdate() throws SQLException {
        LocalDate dataVisita = LocalDate.of(2024, 9, 14);
        VisitaVeterinaria visitaVeterinaria = new VisitaVeterinaria();
        visitaVeterinaria.setData(dataVisita);
        visitaVeterinaria.setCognomeVeterinario("Bortonetti");
        visitaVeterinaria.setCuraPrescritta("Solo fieno");
        visitaVeterinaria.setDiagnosi("Mal di pancia");
        visitaVeterinaria.setNomeVeterinario("Eugenio");
        visitaVeterinaria.setStatoAnimale("Spossato");
        visitaVeterinaria.setProgrammata(true);
        visitaVeterinaria.setIdentificativoAnimale(21);

        when(preparedStatement.executeUpdate()).thenReturn(1);

        visitaVeterinariaDAO.update(visitaVeterinaria);

        verify(connection, times(1)).prepareStatement("UPDATE visita_veterinaria SET diagnosi = ?, nome_veterinario = ?, cognome_veterinario = ?, cura_prescritta = ?, stato_animale = ?, programmata = ? WHERE data = ? and identificativo_animale = ?");

        verify(preparedStatement, times(1)).setString(1, visitaVeterinaria.getDiagnosi());
        verify(preparedStatement, times(1)).setString(2, visitaVeterinaria.getNomeVeterinario());
        verify(preparedStatement, times(1)).setString(3, visitaVeterinaria.getCognomeVeterinario());
        verify(preparedStatement, times(1)).setString(4, visitaVeterinaria.getCuraPrescritta());
        verify(preparedStatement, times(1)).setString(5, visitaVeterinaria.getStatoAnimale());
        verify(preparedStatement, times(1)).setBoolean(6, visitaVeterinaria.getProgrammata());
        verify(preparedStatement, times(1)).setObject(7, visitaVeterinaria.getData());
        verify(preparedStatement, times(1)).setInt(8, visitaVeterinaria.getIdentificativoAnimale());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDelete() throws SQLException {
        VisitaVeterinaria visitaVeterinaria = new VisitaVeterinaria();
        visitaVeterinaria.setData(LocalDate.now());
        visitaVeterinaria.setIdentificativoAnimale(1);

        when(preparedStatement.executeUpdate()).thenReturn(1);

        visitaVeterinariaDAO.delete(visitaVeterinaria);

        verify(connection, times(1)).prepareStatement("DELETE FROM visita_veterinaria WHERE data = ? and identificativo_animale = ?");

        verify(preparedStatement, times(1)).setObject(1, visitaVeterinaria.getData());
        verify(preparedStatement, times(1)).setInt(2, visitaVeterinaria.getIdentificativoAnimale());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    
}
