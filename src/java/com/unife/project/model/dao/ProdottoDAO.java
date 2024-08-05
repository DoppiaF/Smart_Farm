package com.unife.project.model.dao;

import com.unife.project.model.mo.Prodotto;

import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO implements GenericDAO<Prodotto> {
    private List<Prodotto> prodotti = new ArrayList<>();
    private int idCounter = 1;

    @Override
    public void save(Prodotto prodotto) {
        prodotto.setId_prodotto(idCounter++);
        prodotti.add(prodotto);
    }

    @Override
    public void update(Prodotto prodotto) {
        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).getId_prodotto() == prodotto.getId_prodotto()) {
                prodotti.set(i, prodotto);
                return;
            }
        }
    }

    @Override
    public void delete(Prodotto prodotto) {
        prodotti.remove(prodotto);
    }

    @Override
    public Prodotto findById(int id) {
        for (Prodotto prodotto : prodotti) {
            if (prodotto.getId_prodotto() == id) {
                return prodotto;
            }
        }
        return null;
    }

    @Override
    public List<Prodotto> findAll() {
        return new ArrayList<>(prodotti);
    }
}
