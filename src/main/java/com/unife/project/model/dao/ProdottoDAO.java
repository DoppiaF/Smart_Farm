package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Prodotto;
import com.unife.project.model.mo.ProdottoConPrezzo;


public interface ProdottoDAO extends GenericDAO<Prodotto> {
    List<Prodotto> findProdottiUltimoAnno();
    List<Prodotto> findProdottiUltimoMese();
    List<Prodotto> findProdottoUltimoAnnoPerRazza(String specie);
    List<ProdottoConPrezzo> findProdottoUltimoAnnoConPrezzo();
}
