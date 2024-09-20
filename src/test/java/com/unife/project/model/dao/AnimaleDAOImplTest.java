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
}