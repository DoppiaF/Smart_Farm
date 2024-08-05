package com.unife.project.model.mo;

public class ProdottoAnimale {
    private int id_prodotto;
    private int id_animale;

    // Costruttori, getter e setter

    //public ProdottoAnimale() {}

    public ProdottoAnimale(int id_prodotto, int id_animale) {
        this.id_prodotto = id_prodotto;
        this.id_animale = id_animale;
    }

    // Getters and setters

    public int getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public int getId_animale() {
        return id_animale;
    }

    public void setId_animale(int id_animale) {
        this.id_animale = id_animale;
    }
}
