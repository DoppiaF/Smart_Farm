package com.unife.project.model.dao;

import com.unife.project.model.mo.ProdottoAnimale;

import java.util.ArrayList;
import java.util.List;

public class ProdottoAnimaleDAO implements GenericDAO<ProdottoAnimale> {
    private List<ProdottoAnimale> prodottoAnimaleList = new ArrayList<>();

    @Override
    public void save(ProdottoAnimale prodottoAnimale) {
        prodottoAnimaleList.add(prodottoAnimale);
    }

    @Override
    public void update(ProdottoAnimale prodottoAnimale) {
        for (int i = 0; i < prodottoAnimaleList.size(); i++) {
            if (prodottoAnimaleList.get(i).getId_prodotto() == prodottoAnimale.getId_prodotto() &&
                prodottoAnimaleList.get(i).getId_animale() == prodottoAnimale.getId_animale()) {
                prodottoAnimaleList.set(i, prodottoAnimale);
                return;
            }
        }
    }

    @Override
    public void delete(ProdottoAnimale prodottoAnimale) {
        prodottoAnimaleList.remove(prodottoAnimale);
    }

    @Override
    public ProdottoAnimale findById(int id) {
        for (ProdottoAnimale prodottoAnimale : prodottoAnimaleList) {
            if (prodottoAnimale.getId_prodotto() == id) {
                return prodottoAnimale;
            }
        }
        return null;
    }

    @Override
    public List<ProdottoAnimale> findAll() {
        return new ArrayList<>(prodottoAnimaleList);
    }
}
