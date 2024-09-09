package com.unife.project.model.dao;

import java.time.LocalDate;
import java.util.List;

import com.unife.project.model.mo.VisitaVeterinaria;

public interface VisitaVeterinariaDAO extends GenericDAO<VisitaVeterinaria> {
    //metodi specifici per visitaVeterinaria
    List<VisitaVeterinaria> findByDataAndIdentificativoAnimale(LocalDate data, int identificativoAnimale);
    List<VisitaVeterinaria> findByIdAnimale(int idAnimale);
}
