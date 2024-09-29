package com.unife.project.model.dao;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.unife.project.model.mo.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.stefanbirkner.systemlambda.SystemLambda;

public class UtenteDAOImplTest {
    
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;
    
    @InjectMocks
    private UtenteDAOImpl utenteDAO;
    
    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    
    @Test
    public void testSave() throws SQLException, Exception {
        
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        LocalDate dataNascita = LocalDate.of(1990, 1, 1);



        Utente utente = new Utente();
        utente.setUserName("Elisa");
        utente.setPassword("pw1");
        utente.setEmail("eli@gmail.com");
        utente.setCreateTime(createTime);
        utente.setDataNascita(dataNascita);
        utente.setRuolo_raccolta(true);
        utente.setRuolo_irrigazione(false);
        utente.setRuolo_pastore(true);
        utente.setRuolo_admin(false);

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            utenteDAO.save(utente);
        });

        // Verifica che il metodo save abbia chiamato executeUpdate
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("Un nuovo utente è stato inserito correttamente!"));
    }

    @Test
    public void testUpdate() throws SQLException, Exception {
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        LocalDate dataNascita = LocalDate.of(1990, 1, 1);

        Utente utente = new Utente();

        utente.setUserName("Asia");
        utente.setPassword("pw2");
        utente.setEmail("asia@gmail.com");
        utente.setCreateTime(createTime);
        utente.setDataNascita(dataNascita);
        utente.setRuolo_raccolta(false);
        utente.setRuolo_irrigazione(true);
        utente.setRuolo_pastore(false);
        utente.setRuolo_admin(true);

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

      // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            utenteDAO.update(utente);
        });

        // Verifica che il metodo update abbia chiamato executeUpdate
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("Un utente esistente è stato aggiornato correttamente!"));
    
    }

    
    @Test
    public void testDelete() throws SQLException, Exception {
        Utente utente = new Utente();
        utente.setId(1);
        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            utenteDAO.delete(utente);
        });

        // Verifica che il metodo delete abbia chiamato executeUpdate
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("Un utente è stato eliminato correttamente!"));
    }

    @Test
    public void testFindById() throws SQLException, Exception {
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        LocalDate dataNascita = LocalDate.of(1990, 1, 1);

        int utenteId = 1;
        Utente utente = new Utente();
        utente.setId(utenteId);
        utente.setUserName("Elisa");
        utente.setPassword("pw1");
        utente.setEmail("eli@gmail.com");
        utente.setCreateTime(createTime);
        utente.setDataNascita(dataNascita);
        utente.setRuolo_raccolta(true);
        utente.setRuolo_irrigazione(false);
        utente.setRuolo_pastore(true);
        utente.setRuolo_admin(false);

        // Configura il mock per restituire i risultati del ResultSet
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(utente.getId());
        when(resultSet.getString("username")).thenReturn(utente.getUserName());
        when(resultSet.getString("password")).thenReturn(utente.getPassword());
        when(resultSet.getString("email")).thenReturn(utente.getEmail());
        when(resultSet.getTimestamp("create_time")).thenReturn(utente.getCreateTime());
        when(resultSet.getDate("data_nascita")).thenReturn(java.sql.Date.valueOf(utente.getDataNascita()));
        when(resultSet.getBoolean("ruolo_raccolta")).thenReturn(utente.getRuolo_raccolta());
        when(resultSet.getBoolean("ruolo_irrigazione")).thenReturn(utente.getRuolo_irrigazione());
        when(resultSet.getBoolean("ruolo_pastore")).thenReturn(utente.getRuolo_pastore());
        when(resultSet.getBoolean("ruolo_admin")).thenReturn(utente.getRuolo_admin());

        // Configura il mock per restituire il ResultSet quando viene chiamato executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Esegui il metodo findById
        Utente result = utenteDAO.findById(utenteId);

        // Verifica che il metodo findById abbia chiamato executeQuery
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica che l'utente restituito sia quello atteso
        assertNotNull(result);
        assertEquals(utente.getId(), result.getId());
        assertEquals(utente.getUserName(), result.getUserName());
        assertEquals(utente.getPassword(), result.getPassword());
        assertEquals(utente.getEmail(), result.getEmail());
        assertEquals(utente.getCreateTime(), result.getCreateTime());
        assertEquals(utente.getDataNascita(), result.getDataNascita());
        assertEquals(utente.getRuolo_raccolta(), result.getRuolo_raccolta());
        assertEquals(utente.getRuolo_irrigazione(), result.getRuolo_irrigazione());
        assertEquals(utente.getRuolo_pastore(), result.getRuolo_pastore());
        assertEquals(utente.getRuolo_admin(), result.getRuolo_admin());
    }
}
