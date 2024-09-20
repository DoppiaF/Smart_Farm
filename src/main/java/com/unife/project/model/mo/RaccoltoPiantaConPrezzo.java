package com.unife.project.model.mo;

import java.time.LocalDate;

public class RaccoltoPiantaConPrezzo {
        private LocalDate dataRaccolto;
    private double quantita;
    private double prezzo;

    public RaccoltoPiantaConPrezzo(LocalDate dataRaccolto, double quantita, double prezzo) {
        this.dataRaccolto = dataRaccolto;
        this.quantita = quantita;
        this.prezzo = prezzo;
    }

    public LocalDate getDataRaccolto() {
        return dataRaccolto;
    }

    public void setDataRaccolto(LocalDate dataRaccolto) {
        this.dataRaccolto = dataRaccolto;
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
