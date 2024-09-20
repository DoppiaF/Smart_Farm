package com.unife.project.model.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.unife.project.model.mo.Animale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AnimaleDAOImplIntegrationTest {
    private DriverManagerDataSource dataSource;
    private AnimaleDAOImpl animaleDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        try (Connection connection = dataSource.getConnection()) {
            String createTableSql = "CREATE TABLE animale (" +
                    "id_animale INT AUTO_INCREMENT PRIMARY KEY, " +
                    "peso INT, " +
                    "sesso CHAR(1), " +
                    "razza VARCHAR(255), " +
                    "tipo_alimentazione VARCHAR(255), " +
                    "nome_stalla VARCHAR(255), " +
                    "data_nascita DATE, " +
                    "data_ingresso DATE, " +
                    "data_uscita DATE, " +
                    "data_morte DATE, " +
                    "data_vaccino DATE)";
            try (PreparedStatement ps = connection.prepareStatement(createTableSql)) {
                ps.execute();
            }
        }

        animaleDAO = new AnimaleDAOImpl(dataSource.getConnection());
    }

    @Test
    public void testSave() throws SQLException {
        Animale animale = new Animale();
        animale.setPeso(100);
        animale.setSesso('M');
        animale.setRazza("Bovino");
        animale.setTipoAlimentazione("Erba");
        animale.setNomeStalla("Stalla1");
        animale.setData_nascita(LocalDate.of(2020, 1, 1));
        animale.setData_ingresso(LocalDate.of(2021, 1, 1));
        animale.setData_uscita(LocalDate.of(2022, 1, 1));
        animale.setData_morte(LocalDate.of(2023, 1, 1));
        animale.setData_vaccino(LocalDate.of(2024, 1, 1));

        animaleDAO.save(animale);

        try (Connection connection = dataSource.getConnection()) {
            String selectSql = "SELECT * FROM animale WHERE peso = ?";
            try (PreparedStatement ps = connection.prepareStatement(selectSql)) {
                ps.setInt(1, 100);
                try (ResultSet rs = ps.executeQuery()) {
                    assertTrue(rs.next());
                    assertEquals("Bovino", rs.getString("razza"));
                }
            }
        }
    }
}