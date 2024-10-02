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

public class ProdottoDAOImplTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ProdottoDAOImpl prodottoDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException {
        Prodotto prodotto = new Prodotto();
        prodotto.setId_prodotto(1);
        prodotto.setQuantita(100);
        prodotto.setTipoProdotto("Formaggio");
        prodotto.setDataProduzione(LocalDate.of(2023, 10, 1));
        prodotto.setSpecie("Mucca");
        prodotto.setStalla("Stalla1");


        // Configura il mock per restituire il numero di righe inserite
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Esegui il metodo save
        prodottoDAO.save(prodotto);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("INSERT INTO prodotto (quantita, data_produzione, tipo_prodotto, specie, stalla) VALUES (?, ?, ?, ?, ?)");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setInt(1, 100);
        verify(preparedStatement, times(1)).setDate(2, Date.valueOf(LocalDate.of(2023, 10, 1)));
        verify(preparedStatement, times(1)).setString(3, "Formaggio");
        verify(preparedStatement, times(1)).setString(4, "Mucca");
        verify(preparedStatement, times(1)).setString(5, "Stalla1");

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws SQLException {
        Prodotto prodotto = new Prodotto();
        prodotto.setId_prodotto(1);
        prodotto.setQuantita(100);
        prodotto.setTipoProdotto("Formaggio");
        prodotto.setDataProduzione(LocalDate.of(2023, 10, 1));
        prodotto.setSpecie("Mucca");
        prodotto.setStalla("Stalla1");

        // Esegui il metodo update
        prodottoDAO.update(prodotto);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement(
            "UPDATE prodotto SET quantita = ?, data_produzione = ?, tipo_prodotto = ?, specie = ?, stalla = ? WHERE id_prodotto = ?"
        );

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setInt(1, 100);
        verify(preparedStatement, times(1)).setDate(2, Date.valueOf(LocalDate.of(2023, 10, 1)));
        verify(preparedStatement, times(1)).setString(3, "Formaggio");
        verify(preparedStatement, times(1)).setString(4, "Mucca");
        verify(preparedStatement, times(1)).setString(5, "Stalla1");
        verify(preparedStatement, times(1)).setInt(6, 1);

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }


    @Test
    public void testDelete() throws SQLException {
        Prodotto prodotto = new Prodotto();
        prodotto.setId_prodotto(1);
        prodotto.setQuantita(100);
        prodotto.setTipoProdotto("Formaggio");
        prodotto.setDataProduzione(LocalDate.of(2023, 10, 1));
        prodotto.setSpecie("Mucca");
        prodotto.setStalla("Stalla1");

        // Configura il mock per restituire il numero di righe eliminate
        when(preparedStatement.executeUpdate()).thenReturn(1);


        // Esegui il metodo delete
        prodottoDAO.delete(prodotto);

        
        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("DELETE FROM prodotto WHERE id_prodotto = ?");

        // Verifica che il parametro sia stato impostato correttamente
        verify(preparedStatement, times(1)).setInt(1, prodotto.getId_prodotto());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();

    }

    @Test
    public void testFindById() throws SQLException {
        int id = 1;

        // Configura il mock per restituire un ResultSet con dati
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_prodotto")).thenReturn(id);
        when(resultSet.getInt("quantita")).thenReturn(100);
        when(resultSet.getDate("data_produzione")).thenReturn(Date.valueOf(LocalDate.of(2023, 10, 1)));
        when(resultSet.getString("tipo_prodotto")).thenReturn("Formaggio");
        when(resultSet.getString("specie")).thenReturn("Mucca");
        when(resultSet.getString("stalla")).thenReturn("Stalla1");

        // Esegui il metodo findById
        Prodotto prodotto = prodottoDAO.findById(id);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("SELECT * FROM prodotto WHERE id_prodotto = ?");

        // Verifica che il parametro sia stato impostato correttamente
        verify(preparedStatement, times(1)).setInt(1, id);

        // Verifica che il metodo executeQuery sia stato chiamato
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica che il metodo next del ResultSet sia stato chiamato
        verify(resultSet, times(1)).next();

        // Verifica i valori restituiti
        assertNotNull(prodotto);
        assertEquals(id, prodotto.getId_prodotto());
        assertEquals(100, prodotto.getQuantita());
        assertEquals(LocalDate.of(2023, 10, 1), prodotto.getDataProduzione());
        assertEquals("Formaggio", prodotto.getTipoProdotto());
        assertEquals("Mucca", prodotto.getSpecie());
        assertEquals("Stalla1", prodotto.getStalla());
    }

    @Test
    public void testFindAll() throws SQLException, Exception{
        List<Prodotto> prodotti = new ArrayList<>();
        Prodotto prodotto1 = new Prodotto();
        prodotto1.setId_prodotto(1);
        prodotto1.setQuantita(100);
        prodotto1.setTipoProdotto("Formaggio");
        prodotto1.setDataProduzione(LocalDate.of(2023, 10, 1));
        prodotto1.setSpecie("Mucca");
        prodotto1.setStalla("Stalla1");
        
        Prodotto prodotto2 = new Prodotto();
        prodotto2.setId_prodotto(2);
        prodotto2.setQuantita(200);
        prodotto2.setTipoProdotto("Formaggio");
        prodotto2.setDataProduzione(LocalDate.of(2023, 10, 1));
        prodotto2.setSpecie("Mucca");
        prodotto2.setStalla("Stalla1");

        prodotti.add(prodotto1);
        prodotti.add(prodotto2);

        // Configura il mock per restituire i risultati del ResultSet
        when(resultSet.isBeforeFirst()).thenReturn(true, true, false);
        when(resultSet.next()).thenReturn( true, true, false);
        when(resultSet.getInt("id_prodotto")).thenReturn(prodotto1.getId_prodotto(), prodotto2.getId_prodotto());
        when(resultSet.getInt("quantita")).thenReturn(prodotto1.getQuantita(), prodotto2.getQuantita());
        when(resultSet.getDate("data_produzione")).thenReturn(Date.valueOf(prodotto1.getDataProduzione()), Date.valueOf(prodotto2.getDataProduzione()));
        when(resultSet.getString("tipo_prodotto")).thenReturn(prodotto1.getTipoProdotto(), prodotto2.getTipoProdotto());
        when(resultSet.getString("specie")).thenReturn(prodotto1.getSpecie(), prodotto2.getSpecie());
        when(resultSet.getString("stalla")).thenReturn(prodotto1.getStalla(), prodotto2.getStalla());

        // Configura il mock per restituire il ResultSet quando viene chiamato executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Esegui il metodo findAll
        List<Prodotto> result = prodottoDAO.findAll();

        // Verifica che il metodo findAll abbia chiamato executeQuery
        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(2, result.size());
        assertEquals(prodotto1.getId_prodotto(), result.get(0).getId_prodotto());
        assertEquals(prodotto2.getId_prodotto(), result.get(1).getId_prodotto());
        assertEquals(prodotto1.getQuantita(), result.get(0).getQuantita());
        assertEquals(prodotto2.getQuantita(), result.get(1).getQuantita());
        assertEquals(prodotto1.getDataProduzione(), result.get(0).getDataProduzione());
        assertEquals(prodotto2.getDataProduzione(), result.get(1).getDataProduzione());
        assertEquals(prodotto1.getTipoProdotto(), result.get(0).getTipoProdotto());
        assertEquals(prodotto2.getTipoProdotto(), result.get(1).getTipoProdotto());
        assertEquals(prodotto1.getSpecie(), result.get(0).getSpecie());
        assertEquals(prodotto2.getSpecie(), result.get(1).getSpecie());
        assertEquals(prodotto1.getStalla(), result.get(0).getStalla());
        assertEquals(prodotto2.getStalla(), result.get(1).getStalla());
        
    }
}
