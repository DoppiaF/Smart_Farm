package com.unife.project.model.dao;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.unife.project.model.mo.Animale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.stefanbirkner.systemlambda.SystemLambda;


public class AnimaleDAOImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private AnimaleDAOImpl animaleDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    public void testSave() throws SQLException, Exception {
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

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            animaleDAO.save(animale);
        });

        // Verifica che il metodo save abbia chiamato executeUpdate
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("Un nuovo animale è stato inserito correttamente!"));
    }

    @Test
    public void testUpdate() throws SQLException, Exception {
        Animale animale = new Animale();
        animale.setPeso(120);
        animale.setSesso('F');
        animale.setRazza("Ovino");
        animale.setTipoAlimentazione("fieno");
        animale.setNomeStalla("stalla_B");
        animale.setData_nascita(LocalDate.of(2019, 2, 2));
        animale.setData_ingresso(LocalDate.of(2020, 2, 2));
        animale.setData_uscita(LocalDate.of(2021, 2, 2));
        animale.setData_morte(LocalDate.of(2022, 2, 2));
        animale.setData_vaccino(LocalDate.of(2023, 2, 2));

        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            animaleDAO.update(animale);
        });

        // Verifica che il metodo update abbia chiamato executeUpdate
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("L'animale è stato aggiornato correttamente!"));
    }

    @Test
    public void testDelete() throws SQLException, Exception {
        Animale animale;
        animale = new Animale();
        animale.setId_animale(1);
        // Configura il mock per restituire 1 quando viene chiamato executeUpdate
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Cattura l'output del terminale
        String output = SystemLambda.tapSystemOut(() -> {
            animaleDAO.delete(animale);
        });

        // Verifica che il metodo delete abbia chiamato executeUpdate
        verify(preparedStatement, times(1)).executeUpdate();

        // Verifica che il messaggio corretto sia stato stampato nel terminale
        assertTrue(output.contains("L'animale è stato eliminato correttamente!"));
    }

    @Test
    public void testFindById() throws SQLException, Exception {
        int animaleId = 1;
        Animale animale = new Animale();
        animale.setId_animale(animaleId);
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

        // Configura il mock per restituire i risultati del ResultSet
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_animale")).thenReturn(animale.getId_animale());
        when(resultSet.getInt("peso")).thenReturn(animale.getPeso());
        when(resultSet.getString("sesso")).thenReturn(String.valueOf(animale.getSesso()));
        when(resultSet.getString("razza")).thenReturn(animale.getRazza());
        when(resultSet.getString("tipo_alimentazione")).thenReturn(animale.getTipoAlimentazione());
        when(resultSet.getString("nome_stalla")).thenReturn(animale.getNomeStalla());
        when(resultSet.getDate("data_nascita")).thenReturn(java.sql.Date.valueOf(animale.getData_nascita()));
        when(resultSet.getDate("data_ingresso")).thenReturn(java.sql.Date.valueOf(animale.getData_ingresso()));
        when(resultSet.getDate("data_uscita")).thenReturn(java.sql.Date.valueOf(animale.getData_uscita()));
        when(resultSet.getDate("data_morte")).thenReturn(java.sql.Date.valueOf(animale.getData_morte()));
        when(resultSet.getDate("data_vaccino")).thenReturn(java.sql.Date.valueOf(animale.getData_vaccino()));

        // Configura il mock per restituire il ResultSet quando viene chiamato executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Esegui il metodo findById
        Animale result = animaleDAO.findById(animaleId);

        // Verifica che il metodo findById abbia chiamato executeQuery
        verify(preparedStatement, times(1)).executeQuery();

        // Verifica che l'animale restituito sia quello atteso
        assertNotNull(result);
        assertEquals(animale.getId_animale(), result.getId_animale());
        assertEquals(animale.getPeso(), result.getPeso());
        assertEquals(animale.getSesso(), result.getSesso());
        assertEquals(animale.getRazza(), result.getRazza());
        assertEquals(animale.getTipoAlimentazione(), result.getTipoAlimentazione());
        assertEquals(animale.getNomeStalla(), result.getNomeStalla());
        assertEquals(animale.getData_nascita(), result.getData_nascita());
        assertEquals(animale.getData_ingresso(), result.getData_ingresso());
        assertEquals(animale.getData_uscita(), result.getData_uscita());
        assertEquals(animale.getData_morte(), result.getData_morte());
        assertEquals(animale.getData_vaccino(), result.getData_vaccino());
    }
}