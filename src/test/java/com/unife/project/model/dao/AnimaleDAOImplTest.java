package com.unife.project.model.dao;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.unife.project.model.mo.Animale;

import java.sql.Connection;
import java.sql.Date;
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


    @Test
    public void testFindAll() throws SQLException {
        List<Animale> animali = new ArrayList<>();
        Animale animale1 = new Animale();
        animale1.setId_animale(1);
        animale1.setPeso(500);
        animale1.setSesso('M');
        animale1.setRazza("Razza1");
        animale1.setTipoAlimentazione("Erba");
        animale1.setNomeStalla("Stalla1");
        animale1.setData_nascita(LocalDate.of(2020, 1, 1));
        animale1.setData_ingresso(LocalDate.of(2020, 2, 1));
        animale1.setData_uscita(LocalDate.of(2020, 3, 1));
        animale1.setData_morte(LocalDate.of(2020, 4, 1));
        animale1.setData_vaccino(LocalDate.of(2020, 5, 1));

        Animale animale2 = new Animale();
        animale2.setId_animale(2);
        animale2.setPeso(600);
        animale2.setSesso('F');
        animale2.setRazza("Razza2");
        animale2.setTipoAlimentazione("Fieno");
        animale2.setNomeStalla("Stalla2");
        animale2.setData_nascita(LocalDate.of(2021, 1, 1));
        animale2.setData_ingresso(LocalDate.of(2021, 2, 1));
        animale2.setData_uscita(LocalDate.of(2021, 3, 1));
        animale2.setData_morte(LocalDate.of(2021, 4, 1));
        animale2.setData_vaccino(LocalDate.of(2021, 5, 1));

        animali.add(animale1);
        animali.add(animale2);

        // Configura il mock per restituire i risultati del ResultSet
        
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.isBeforeFirst()).thenReturn(true, true, false);
        when(resultSet.getInt("id_animale")).thenReturn(animale1.getId_animale(), animale2.getId_animale());
        when(resultSet.getInt("peso")).thenReturn(animale1.getPeso(), animale2.getPeso());
        when(resultSet.getString("sesso")).thenReturn(String.valueOf(animale1.getSesso()), String.valueOf(animale2.getSesso()));
        when(resultSet.getString("razza")).thenReturn(animale1.getRazza(), animale2.getRazza());
        when(resultSet.getString("tipo_alimentazione")).thenReturn(animale1.getTipoAlimentazione(), animale2.getTipoAlimentazione());
        when(resultSet.getString("nome_stalla")).thenReturn(animale1.getNomeStalla(), animale2.getNomeStalla());
        when(resultSet.getDate("data_nascita")).thenReturn(Date.valueOf(animale1.getData_nascita()), Date.valueOf(animale2.getData_nascita()));
        when(resultSet.getDate("data_ingresso")).thenReturn(Date.valueOf(animale1.getData_ingresso()), Date.valueOf(animale2.getData_ingresso()));
        when(resultSet.getDate("data_uscita")).thenReturn(Date.valueOf(animale1.getData_uscita()), Date.valueOf(animale2.getData_uscita()));
        when(resultSet.getDate("data_morte")).thenReturn(Date.valueOf(animale1.getData_morte()), Date.valueOf(animale2.getData_morte()));
        when(resultSet.getDate("data_vaccino")).thenReturn(Date.valueOf(animale1.getData_vaccino()), Date.valueOf(animale2.getData_vaccino()));  
    
        // Configura il mock per restituire il ResultSet quando viene chiamato executeQuery
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        List<Animale> result = animaleDAO.findAll();

        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(2, result.size());
        
        assertEquals(animale1.getId_animale(), result.get(0).getId_animale());
        assertEquals(animale1.getPeso(), result.get(0).getPeso());
        assertEquals(animale1.getSesso(), result.get(0).getSesso());
        assertEquals(animale1.getRazza(), result.get(0).getRazza());
        assertEquals(animale1.getTipoAlimentazione(), result.get(0).getTipoAlimentazione());
        assertEquals(animale1.getNomeStalla(), result.get(0).getNomeStalla());
        assertEquals(animale1.getData_nascita(), result.get(0).getData_nascita());
        assertEquals(animale1.getData_ingresso(), result.get(0).getData_ingresso());
        assertEquals(animale1.getData_uscita(), result.get(0).getData_uscita());
        assertEquals(animale1.getData_morte(), result.get(0).getData_morte());
        assertEquals(animale1.getData_vaccino(), result.get(0).getData_vaccino());


        assertEquals(animale2.getId_animale(), result.get(1).getId_animale());
        assertEquals(animale2.getPeso(), result.get(1).getPeso());
        assertEquals(animale2.getSesso(), result.get(1).getSesso());
        assertEquals(animale2.getRazza(), result.get(1).getRazza());
        assertEquals(animale2.getTipoAlimentazione(), result.get(1).getTipoAlimentazione());
        assertEquals(animale2.getNomeStalla(), result.get(1).getNomeStalla());
        assertEquals(animale2.getData_nascita(), result.get(1).getData_nascita());
        assertEquals(animale2.getData_ingresso(), result.get(1).getData_ingresso());
        assertEquals(animale2.getData_uscita(), result.get(1).getData_uscita());
        assertEquals(animale2.getData_morte(), result.get(1).getData_morte());
        assertEquals(animale2.getData_vaccino(), result.get(1).getData_vaccino());

    }
}