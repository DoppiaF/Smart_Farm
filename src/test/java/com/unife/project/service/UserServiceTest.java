package com.unife.project.service;

import org.junit.jupiter.api.Test;
import com.unife.project.model.mo.Utente;
import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest {

    @Test
    public void testAuthenticate() {
        // Inizializza il servizio utente
        UserService userService = new UserService();

        // Dati di test
        String testUsername = "Ale";
        String testPassword = "AZ";

        // Autentica l'utente
        Utente utente = userService.authenticate(testUsername, testPassword);

        // Verifica il risultato
        if (utente != null) {
            System.out.println("Autenticazione riuscita!");
            System.out.println("Username: " + utente.getUserName());
            System.out.println("Password: " + utente.getPassword());
        } else {
            System.out.println("Autenticazione fallita!");
        }

        // Asserzioni per verificare il risultato
        assertNotNull(utente, "L'utente non dovrebbe essere null");
        assertEquals(testUsername, utente.getUserName(), "Lo username dovrebbe corrispondere");
        assertEquals(testPassword, utente.getPassword(), "La password dovrebbe corrispondere");
    
    }
}
