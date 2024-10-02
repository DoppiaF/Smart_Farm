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

    @Test
    public void testFindAll() throws SQLException, Exception{
        List<VisitaVeterinaria> visite = new ArrayList<>();

        VisitaVeterinaria visita1 = new VisitaVeterinaria();
        visita1.setData(LocalDate.of(2024, 07, 30));
        visita1.setCognomeVeterinario("Zorzi");
        visita1.setCuraPrescritta("Solo crocchette");
        visita1.setDiagnosi("Mal di stomaco");
        visita1.setNomeVeterinario("Oreste");
        visita1.setStatoAnimale("Spossato");
        visita1.setProgrammata(false);
        visita1.setIdentificativoAnimale(10);
        VisitaVeterinaria visita2 = new VisitaVeterinaria();
        visita2.setData(LocalDate.of(2024, 9, 14));
        visita2.setCognomeVeterinario("Bortonetti");
        visita2.setCuraPrescritta("Solo fieno");
        visita2.setDiagnosi("Mal di pancia");
        visita2.setNomeVeterinario("Eugenio");
        visita2.setStatoAnimale("Spossato");
        visita2.setProgrammata(true);
        visita2.setIdentificativoAnimale(21);

        visite.add(visita1);
        visite.add(visita2);

        when(resultSet.isBeforeFirst()).thenReturn(true, true, false);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getDate("data")).thenReturn(Date.valueOf(visita1.getData()), Date.valueOf(visita2.getData()));
        when(resultSet.getString("diagnosi")).thenReturn(visita1.getDiagnosi(), visita2.getDiagnosi());
        when(resultSet.getInt("identificativo_animale")).thenReturn(visita1.getIdentificativoAnimale(), visita2.getIdentificativoAnimale());
        when(resultSet.getString("nome_veterinario")).thenReturn(visita1.getNomeVeterinario(), visita2.getNomeVeterinario());
        when(resultSet.getString("cognome_veterinario")).thenReturn(visita1.getCognomeVeterinario(), visita2.getCognomeVeterinario());
        when(resultSet.getString("cura_prescritta")).thenReturn(visita1.getCuraPrescritta(), visita2.getCuraPrescritta());
        when(resultSet.getString("stato_animale")).thenReturn(visita1.getStatoAnimale(), visita2.getStatoAnimale());
        when(resultSet.getBoolean("programmata")).thenReturn(visita1.getProgrammata(), visita2.getProgrammata());

        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        List<VisitaVeterinaria> result = visitaVeterinariaDAO.findAll();

        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(2, result.size());
        assertEquals(visita1.getData(), result.get(0).getData());
        assertEquals(visita2.getData(), result.get(1).getData());
        assertEquals(visita1.getDiagnosi(), result.get(0).getDiagnosi());
        assertEquals(visita2.getDiagnosi(), result.get(1).getDiagnosi());
        assertEquals(visita1.getIdentificativoAnimale(), result.get(0).getIdentificativoAnimale());
        assertEquals(visita2.getIdentificativoAnimale(), result.get(1).getIdentificativoAnimale());
        assertEquals(visita1.getNomeVeterinario(), result.get(0).getNomeVeterinario());
        assertEquals(visita2.getNomeVeterinario(), result.get(1).getNomeVeterinario());
        assertEquals(visita1.getCognomeVeterinario(), result.get(0).getCognomeVeterinario());
        assertEquals(visita2.getCognomeVeterinario(), result.get(1).getCognomeVeterinario());
        assertEquals(visita1.getCuraPrescritta(), result.get(0).getCuraPrescritta());
        assertEquals(visita2.getCuraPrescritta(), result.get(1).getCuraPrescritta());
        assertEquals(visita1.getStatoAnimale(), result.get(0).getStatoAnimale());
        assertEquals(visita2.getStatoAnimale(), result.get(1).getStatoAnimale());
        assertEquals(visita1.getProgrammata(), result.get(0).getProgrammata());
        assertEquals(visita2.getProgrammata(), result.get(1).getProgrammata());





    }
    
}
