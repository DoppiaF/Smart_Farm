package com.unife.project.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseConnectionTest {

    @Test
    public void testDatabaseConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            assertNotNull(connection, "Connessione al database fallita!");
            if (connection != null) {
                System.out.println("Connessione al database riuscita!");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Eccezione durante la connessione al database: " + e.getMessage());
        }
    }
}