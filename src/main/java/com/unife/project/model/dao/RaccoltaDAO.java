package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Raccolta;
import com.unife.project.model.mo.RaccoltaPerAnno;
import com.unife.project.model.mo.RaccoltoPiantaConPrezzo;
import java.time.LocalDate;

public interface RaccoltaDAO extends GenericDAO<Raccolta>{
    //metodi specifici per raccolta
    List<RaccoltaPerAnno> findByPiantagione20Anni(int id_piantagione);
    public List<RaccoltoPiantaConPrezzo> findRaccoltaUltimoAnnoConPrezzo();
    public List<Raccolta> findByIdPiantagioneUltimoAnno(int id_piantagione);
    public List<Raccolta> findByIdPiantagioneAndMese(int id_piantagione, LocalDate meseAnno);
}
