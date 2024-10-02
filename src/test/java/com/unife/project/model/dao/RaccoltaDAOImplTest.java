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

    @Test
    public void testFindAll() throws SQLException,Exception{
        List<Raccolta> raccolte = new ArrayList<Raccolta>();
        Raccolta raccolta1 = new Raccolta();
        raccolta1.setId_raccolta(1);
        raccolta1.setTipoPianta("bietola");
        raccolta1.setQuantita(100);
        raccolta1.setDataRaccolta(LocalDate.of(2023, 10, 1));
        raccolta1.setStato("completata");
        raccolta1.setMacchinario("falce");
        raccolta1.setOperatore(1);
        raccolta1.setId_piantagione(1);

        Raccolta raccolta2 = new Raccolta();
        raccolta2.setId_raccolta(2);
        raccolta2.setTipoPianta("ginepro");
        raccolta2.setQuantita(10);
        raccolta2.setDataRaccolta(LocalDate.of(2024, 10, 1));
        raccolta2.setStato("non completata");
        raccolta2.setMacchinario("martello");
        raccolta2.setOperatore(2);
        raccolta2.setId_piantagione(2);

        raccolte.add(raccolta1);
        raccolte.add(raccolta2);

        when(resultSet.isBeforeFirst()).thenReturn(true, true, false);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id_raccolta")).thenReturn(raccolta1.getId_raccolta(),raccolta2.getId_raccolta());
        when(resultSet.getString("tipo_pianta")).thenReturn(raccolta1.getTipoPianta(), raccolta2.getTipoPianta());
        when(resultSet.getInt("quantita")).thenReturn(raccolta1.getQuantita(), raccolta2.getQuantita());
        when(resultSet.getDate("data_raccolta")).thenReturn(Date.valueOf(raccolta1.getDataRaccolta()), Date.valueOf(raccolta2.getDataRaccolta()));
        when(resultSet.getString("stato")).thenReturn(raccolta1.getStato(), raccolta2.getStato());
        when(resultSet.getString("macchinario_usato")).thenReturn(raccolta1.getMacchinario(), raccolta2.getMacchinario());
        when(resultSet.getInt("operatore")).thenReturn(raccolta1.getOperatore(), raccolta2.getOperatore());
        when(resultSet.getInt("id_piantagione")).thenReturn(raccolta1.getId_piantagione(), raccolta2.getId_piantagione());

        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        List<Raccolta> raccolteTrovate = raccoltaDAO.findAll();

        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(2, raccolteTrovate.size());

        assertEquals(raccolta1.getId_raccolta(), raccolteTrovate.get(0).getId_raccolta());
        assertEquals(raccolta1.getTipoPianta(), raccolteTrovate.get(0).getTipoPianta());
        assertEquals(raccolta1.getQuantita(), raccolteTrovate.get(0).getQuantita());
        assertEquals(raccolta1.getDataRaccolta(), raccolteTrovate.get(0).getDataRaccolta());
        assertEquals(raccolta1.getStato(), raccolteTrovate.get(0).getStato());
        assertEquals(raccolta1.getMacchinario(), raccolteTrovate.get(0).getMacchinario());
        assertEquals(raccolta1.getOperatore(), raccolteTrovate.get(0).getOperatore());
        assertEquals(raccolta1.getId_piantagione(), raccolteTrovate.get(0).getId_piantagione());

        assertEquals(raccolta2.getId_raccolta(), raccolteTrovate.get(1).getId_raccolta());
        assertEquals(raccolta2.getTipoPianta(), raccolteTrovate.get(1).getTipoPianta());
        assertEquals(raccolta2.getQuantita(), raccolteTrovate.get(1).getQuantita());
        assertEquals(raccolta2.getDataRaccolta(), raccolteTrovate.get(1).getDataRaccolta());
        assertEquals(raccolta2.getStato(), raccolteTrovate.get(1).getStato());
        assertEquals(raccolta2.getMacchinario(), raccolteTrovate.get(1).getMacchinario());
        assertEquals(raccolta2.getOperatore(), raccolteTrovate.get(1).getOperatore());
        assertEquals(raccolta2.getId_piantagione(), raccolteTrovate.get(1).getId_piantagione());

    }
}
