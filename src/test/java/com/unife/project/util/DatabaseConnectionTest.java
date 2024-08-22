package com.unife.project.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class DatabaseConnectionTest {

    @Test
    public void testDatabaseConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            assertNotNull("Connessione al database fallita!", connection);
            if (connection != null) {
                System.out.println("Connessione al database riuscita!");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Puoi anche aggiungere un'asserzione per fallire il test in caso di eccezione
            org.junit.Assert.fail("Eccezione durante la connessione al database: " + e.getMessage());
        }
    }
}