package com.unife.project.model.mo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Prodotto {
    private int id_prodotto;
    private int quantita;
    private String tipoProdotto;  //FK per listino prezzi
    private LocalDateTime dataProduzione;
    private LocalDateTime dataScadenza;
    private List<Animale> animali = new ArrayList<>();    //FK riferita a Animale

    public Prodotto(int id_prodotto, int quantita, String tipoProdotto, LocalDateTime dataProduzione, LocalDateTime dataScadenza) {
        this.id_prodotto = id_prodotto;
        this.quantita = quantita;
        this.tipoProdotto = tipoProdotto;
        this.dataProduzione = dataProduzione; 
        this.dataScadenza = dataScadenza;
    }

    public int getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getTipoProdotto() {
        return tipoProdotto;
    }

    public void setTipoProdotto(String tipoProdotto) {
        this.tipoProdotto = tipoProdotto;
    }

    public LocalDateTime getDataProduzione() {
        return dataProduzione;
    }

    public void setDataProduzione(LocalDateTime dataProduzione) {
        this.dataProduzione = dataProduzione;
    }

    public LocalDateTime getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDateTime dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public List<Animale> getAnimali() {
        return animali;
    }

    public void setAnimali(List<Animale> animali) {
        this.animali = animali;
    }


}
