package com.unife.project.model.mo;

import java.time.LocalDateTime;

public class prodotto {
    private int id_prodotto;
    private int quantita;
    private String tipoProdotto;  //FK per listino prezzi
    private LocalDateTime dataProduzione;
    private LocalDateTime dataScadenza;

    public prodotto(int id_prodotto, int quantita, String tipoProdotto, LocalDateTime dataProduzione) {
        this.id_prodotto = id_prodotto;
        this.quantita = quantita;
        this.tipoProdotto = tipoProdotto;
        this.dataProduzione = dataProduzione;
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




}
