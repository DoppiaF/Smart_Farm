package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.ProdottoAnimale;

public interface ProdottoAnimaleDAO extends GenericDAO<ProdottoAnimale>{
    List<ProdottoAnimale> findById_animale(int id_animale);
    List<ProdottoAnimale> findById_prodotto(int id_prodotto);
}
