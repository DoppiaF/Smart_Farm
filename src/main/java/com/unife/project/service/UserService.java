package com.unife.project.service;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Utente;

public class UserService {

    Utente utente = null;
    public Utente authenticate(String username, String password){
        
        utente = DAOFactory.getUtenteDAO().findByUsernameAndPassword(username, password);

        return utente;
    }
            
    
}
