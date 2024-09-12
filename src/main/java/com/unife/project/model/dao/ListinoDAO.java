package com.unife.project.model.dao;

import java.util.List;

import com.unife.project.model.mo.Listino;

public interface ListinoDAO extends GenericDAO<Listino> {
    //metodi specifici per listino da definire.
    List<Listino> findByTipoProdotto(String tipo_prodotto);
    List<Listino> findAllPrezzoAggiornato();
}
