package com.unife.project.model.dao;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.unife.project.model.mo.Stalla;

import javafx.util.converter.LocalTimeStringConverter;
import net.bytebuddy.asm.Advice.Local;


public class StallaDAOImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;
    
    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private StallaDAOImpl stallaDAO;

    //private Stalla stalla;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

        @Test
    public void testSave() throws SQLException {
        Stalla stalla = new Stalla();
        stalla.setEtichettaStalla("ovini-1");
        stalla.setCapienza(40);
        stalla.setRazza("ovino-merino");
        stalla.setOraPranzo(LocalTime.of(11,45));
        stalla.setOraCena(LocalTime.of(21,10));

        when(preparedStatement.executeUpdate()).thenReturn(1);

        stallaDAO.save(stalla);

        verify(connection, times(1)).prepareStatement("INSERT INTO stalla (etichetta_stalla, capienza, razza, ora_pranzo, ora_cena) VALUES (?, ?, ?, ?, ?)");


        verify(preparedStatement, times(1)).setString(1, stalla.getEtichettaStalla());
        verify(preparedStatement, times(1)).setInt(2, stalla.getCapienza());
        verify(preparedStatement, times(1)).setString(3, stalla.getRazza());
        verify(preparedStatement, times(1)).setObject(4, stalla.getOraPranzo());
        verify(preparedStatement, times(1)).setObject(5, stalla.getOraCena());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws SQLException {
        Stalla stalla = new Stalla();
        stalla.setEtichettaStalla("ovini-1");
        stalla.setCapienza(40);
        stalla.setRazza("ovino-cashemere");
        stalla.setOraPranzo(LocalTime.of(11,45));
        stalla.setOraCena(LocalTime.of(21,10));

        when(preparedStatement.executeUpdate()).thenReturn(1);

        stallaDAO.update(stalla);

        verify(connection, times(1)).prepareStatement("UPDATE stalla SET capienza = ?, razza = ?, ora_pranzo = ?, ora_cena = ? WHERE etichetta_stalla = ?");

        verify(preparedStatement, times(1)).setInt(1, stalla.getCapienza());
        verify(preparedStatement, times(1)).setString(2, stalla.getRazza());
        verify(preparedStatement, times(1)).setObject(3, stalla.getOraPranzo());
        verify(preparedStatement, times(1)).setObject(4, stalla.getOraCena());
        verify(preparedStatement, times(1)).setString(5, stalla.getEtichettaStalla());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDelete() throws SQLException {
        Stalla stalla = new Stalla();
        stalla.setEtichettaStalla("bovini-1");
        stalla.setCapienza(60);
        stalla.setRazza("bovino-Frisona");
        stalla.setOraPranzo(LocalTime.of(12,0));
        stalla.setOraCena(LocalTime.of(21,20));
        
        when(preparedStatement.executeUpdate()).thenReturn(1);

        stallaDAO.delete(stalla);
                
        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("DELETE FROM stalla WHERE etichetta_stalla = ?");

        verify(preparedStatement, times(1)).setString(1, stalla.getEtichettaStalla());

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeUpdate();

    }


    @Test
    public void testFindByEtichetta() throws SQLException {
        Stalla expectedStalla = new Stalla();
        String etichettaStalla = "bovini-2";

        expectedStalla.setEtichettaStalla(etichettaStalla);
        expectedStalla.setCapienza(60);
        expectedStalla.setRazza("bovino-Ayrshire");
        expectedStalla.setOraPranzo(LocalTime.of(12,0));
        expectedStalla.setOraCena(LocalTime.of(21,20));
        
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("etichetta_stalla")).thenReturn(expectedStalla.getEtichettaStalla());
        when(resultSet.getInt("capienza")).thenReturn(expectedStalla.getCapienza());
        when(resultSet.getString("razza")).thenReturn(expectedStalla.getRazza());
        when(resultSet.getTime("ora_pranzo")).thenReturn(java.sql.Time.valueOf(expectedStalla.getOraPranzo()));
        when(resultSet.getTime("ora_cena")).thenReturn(java.sql.Time.valueOf(expectedStalla.getOraCena()));
        
        Stalla actualStalla = stallaDAO.findByEtichetta(etichettaStalla);
        // Verifica che il metodo prepareStatement sia stato chiamato con la query corretta
        verify(connection, times(1)).prepareStatement("SELECT * FROM stalla WHERE etichetta_stalla = ?");

        verify(preparedStatement, times(1)).setString(1, etichettaStalla);

        // Verifica che il metodo executeUpdate sia stato chiamato
        verify(preparedStatement, times(1)).executeQuery();

        assertNotNull(actualStalla);
        assertEquals(expectedStalla.getEtichettaStalla(), actualStalla.getEtichettaStalla());
        assertEquals(expectedStalla.getCapienza(), actualStalla.getCapienza());
        assertEquals(expectedStalla.getRazza(), actualStalla.getRazza());
        assertEquals(expectedStalla.getOraPranzo(), actualStalla.getOraPranzo());
        assertEquals(expectedStalla.getOraCena(), actualStalla.getOraCena());

    }
}