package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Raccolta;
import com.unife.project.model.mo.RaccoltaPerAnno;

public interface RaccoltaDAO extends GenericDAO<Raccolta>{
    //metodi specifici per raccolta
    List<RaccoltaPerAnno> findByPiantagione20Anni(int id_piantagione);
}
