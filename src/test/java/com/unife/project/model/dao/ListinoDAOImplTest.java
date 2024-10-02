package com.unife.project.model.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.unife.project.model.mo.Listino;

public class ListinoDAOImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ListinoDAOImpl listinoDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException {
        Listino listino = new Listino();
        listino.setTipo_prodotto("Frutta");
        listino.setPrezzo(10.5f);

        // Configura il mock per restituire il numero di righe inserite
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Esegui il metodo save
        listinoDAO.save(listino);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("INSERT INTO listino (tipo_prodotto, prezzo) VALUES (?, ?)");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setString(1, listino.getTipo_prodotto());
        verify(preparedStatement, times(1)).setFloat(2, listino.getPrezzo());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws SQLException {
        Listino listino = new Listino();
        listino.setTipo_prodotto("Frutta");
        listino.setPrezzo(12.0f);

        // Configura il mock per restituire il numero di righe aggiornate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Esegui il metodo update
        listinoDAO.update(listino);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("UPDATE listino SET prezzo = ? WHERE tipo_prodotto = ?");

        // Verifica che i parametri siano stati impostati correttamente
        verify(preparedStatement, times(1)).setFloat(1, listino.getPrezzo());
        verify(preparedStatement, times(1)).setString(2, listino.getTipo_prodotto());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDelete() throws SQLException {
        Listino listino = new Listino();
        listino.setPrezzo(10.5f);
        listino.setTipo_prodotto("Frutta");
        // Configura il mock per restituire il numero di righe eliminate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Esegui il metodo delete
        listinoDAO.delete(listino);

        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("DELETE FROM listino WHERE tipo_prodotto = ?");

        // Verifica che il parametro sia stato impostato correttamente
        verify(preparedStatement, times(1)).setString(1, listino.getTipo_prodotto());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testFindAll() throws SQLException, Exception{
        List<Listino> listini = new ArrayList<Listino>();
        
        Listino listino1 = new Listino();
        listino1.setTipo_prodotto("Frutta");
        listino1.setPrezzo(10.5f);
        Listino listino2 = new Listino();
        listino2.setTipo_prodotto("Verdura");
        listino2.setPrezzo(12.0f);

        listini.add(listino1);
        listini.add(listino2);

        //configura il mock per restituire i risultati del ResultSet
        when(resultSet.next()).thenReturn( true, true, false);
        when(resultSet.isBeforeFirst()).thenReturn(true,true,false);
        when(resultSet.getString("tipo_prodotto")).thenReturn(listino1.getTipo_prodotto(), listino2.getTipo_prodotto());
        when(resultSet.getFloat("prezzo")).thenReturn(listino1.getPrezzo(), listino2.getPrezzo());

        //configura il mock per restituire il ResultSet quando viene chiamato executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        //esegui il metodo findAll
        List<Listino> result = listinoDAO.findAll();

        //verifica che il metodo findAll abbia chiamato executeQuery
        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(2, result.size());
        assertEquals(listino1.getTipo_prodotto(), result.get(0).getTipo_prodotto());
        assertEquals(listino2.getTipo_prodotto(), result.get(1).getTipo_prodotto());
        assertEquals(listino1.getPrezzo(), result.get(0).getPrezzo());
        assertEquals(listino2.getPrezzo(), result.get(1).getPrezzo());

    }
}