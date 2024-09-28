package com.unife.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseConnection {

    //modifiche per selezionare le variabili d'ambiente docker hub se presenti
    static Connection connection = null;
    static String databaseName = System.getenv("MYSQL_DATABASE") != null ? System.getenv("MYSQL_DATABASE") : "fattoria";
    //private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:" + System.getenv("MYSQL_PORT") + "/" + databaseName;
    //private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:" + (System.getenv("MYSQL_PORT") != null ? System.getenv("MYSQL_PORT") : "3306") + "/" + databaseName;
    
    //utilizzo un metodo statico che controlla se l'applicazione sta funzionando in locale o all'interno di un container docker, quindi imposta il nome
    //del servizio db da inserire nell'url per la connessione
    private static String dbServiceName = "localhost";
    static{
        
        if(isRunningInsideDocker()) dbServiceName = System.getenv("MYSQL_HOST"); 
    }
    
    //private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://db:" + (System.getenv("MYSQL_PORT") != null ? System.getenv("MYSQL_PORT") : "3306") + "/" + databaseName;
    private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://" + dbServiceName + ":" + (System.getenv("MYSQL_PORT") != null ? System.getenv("MYSQL_PORT") : "3306") + "/" + databaseName;

    
    private static final String USER = System.getenv("MYSQL_USER") != null ? System.getenv("MYSQL_USER") : "root";
    private static final String PASSWORD = System.getenv("MYSQL_ROOT_PASSWORD") != null ? System.getenv("MYSQL_ROOT_PASSWORD") : "Pannocchie98!?";

    static {
        try {
            // Carica il driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        
        System.out.println("la password usata per la connessione è: " + PASSWORD);
        
        System.out.println("il nome del servizio db è: " + dbServiceName);

        if (connection == null || connection.isClosed()) {
            //String encodedPassword = URLEncoder.encode(PASSWORD, StandardCharsets.UTF_8.toString());
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Boolean isRunningInsideDocker() {

        try (Stream < String > stream =
            Files.lines(Paths.get("/proc/1/cgroup"))) {
            return stream.anyMatch(line -> line.contains("/docker"));
        } catch (IOException e) {
            return false;
        }
    }
}