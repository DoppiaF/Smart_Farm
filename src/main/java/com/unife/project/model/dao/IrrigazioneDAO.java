package com.unife.project.model.dao;

import java.util.List;

//import java.time.LocalDate;
//import java.util.List;
import com.unife.project.model.mo.Irrigazione;

public interface IrrigazioneDAO extends GenericDAO<Irrigazione>{
    public List<Irrigazione> findAllWCisterna();
    //metodi specifici per irrigazioneDAO oltre ai CRUD già richiesti
    
    //List<Irrigazione> findByDate(LocalDate date);  //esempi di metodi implementabili. definire quelli davvero utilizzati
    
    //List<Irrigazione> findByDateRange(LocalDate startDate, LocalDate endDate);
    
    //List<Irrigazione> findByStatus(String status);
    
    //List<Irrigazione> findByType(String type);
    
    //void updateStatus(int id, String newStatus);
    
    //int countByStatus(String status);
}
