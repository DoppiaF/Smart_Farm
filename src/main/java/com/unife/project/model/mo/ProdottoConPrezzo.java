package com.unife.project.model.mo;

import java.time.LocalDate;

public class ProdottoConPrezzo {
    private LocalDate dataProduzione;
    private double quantita;
    private double prezzo;

    public ProdottoConPrezzo(LocalDate dataProduzione, double quantita, double prezzo) {
        this.dataProduzione = dataProduzione;
        this.quantita = quantita;
        this.prezzo = prezzo;
    }

    public LocalDate getDataProduzione() {
        return dataProduzione;
    }

    public void setDataProduzione(LocalDate dataProduzione) {
        this.dataProduzione = dataProduzione;
    }

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }   
}
