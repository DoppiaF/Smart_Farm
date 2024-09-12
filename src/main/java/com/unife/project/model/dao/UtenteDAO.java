package com.unife.project.model.dao;


import com.unife.project.model.mo.Utente;

public interface UtenteDAO extends GenericDAO<Utente> {
    //public Utente findByUsername(String username);
    public Utente findByEmail(String email);
    public Utente findByUsernameAndPassword(String username, String password);
    //public Utente findByEmailAndPassword(String email, String password);
    //public void updatePassword(Utente utente, String newPassword);
    //public void updateEmail(Utente utente, String newEmail);
    //public void updateRuoli(Utente utente, Boolean ruolo_raccolta, Boolean ruolo_irrigazione, Boolean ruolo_pastore, Boolean ruolo_admin);
    //public void updateDataNascita(Utente utente, LocalDate newDataNascita);
    //public void delete(Utente utente);
    //public void deleteAll();
}
