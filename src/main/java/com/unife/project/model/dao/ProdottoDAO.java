package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Prodotto;


public interface ProdottoDAO extends GenericDAO<Prodotto> {
    List<Prodotto> findProdottiUltimoAnno();
    List<Prodotto> findProdottiUltimoMese();
}
