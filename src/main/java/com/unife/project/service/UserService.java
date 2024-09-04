package com.unife.project.service;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Utente;
import com.unife.project.model.dao.UtenteDAO;

public class UserService {

    public Utente authenticate(String username, String password){
        UtenteDAO utenteDAO = DAOFactory.getUtenteDAO();
        
        Utente utente = utenteDAO.findByUsernameAndPassword(username, password);

        return utente;
    }
            
    
}
