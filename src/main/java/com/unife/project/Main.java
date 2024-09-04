package com.unife.project;


import com.unife.project.model.mo.Utente;
import com.unife.project.service.UserService;

public class Main {
    public static void main(String[] args) {
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
    }
}