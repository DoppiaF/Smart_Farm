package com.unife.project.model.mo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prodotto {
    private int id_prodotto;
    private int quantita;
    private String tipo_prodotto;  //FK per listino prezzi
    private LocalDate data_produzione;
    private String specie;  //FK per animale
    private String stalla;  //FK per stalla
    private List<Animale> animali = new ArrayList<>();    //FK riferita a Animale

    //costruttore vuoto
    public Prodotto() {
    }

    public Prodotto( int quantita, String tipoProdotto, LocalDate dataProduzione, String specie, String stalla) {
        //this.id_prodotto = id_prodotto;
        this.quantita = quantita;
        this.tipo_prodotto = tipoProdotto;
        this.data_produzione = dataProduzione; 
        this.specie = specie;
        this.stalla = stalla;
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
        return tipo_prodotto;
    }

    public void setTipoProdotto(String tipoProdotto) {
        this.tipo_prodotto = tipoProdotto;
    }

    public LocalDate getDataProduzione() {
        return data_produzione;
    }

    public void setDataProduzione(LocalDate dataProduzione) {
        this.data_produzione = dataProduzione;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getStalla() {
        return stalla;
    }

    public void setStalla(String stalla) {
        this.stalla = stalla;
    }

    public List<Animale> getAnimali() {
        return animali;
    }

    public void setAnimali(List<Animale> animali) {
        this.animali = animali;
    }


    @Override
    public String toString() {
        return "Prodotto{id_prodotto=" + id_prodotto + 
        ", quantita=" + quantita + 
        ", dataProduzione=" + data_produzione + 
        ", tipoProdotto=" + tipo_prodotto +
        ", specie=" + specie +
        ", stalla=" + stalla +
        '}';
    }
}
