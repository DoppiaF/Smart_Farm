package com.unife.project.model.mo;

import java.time.LocalDate;

public class Raccolta {
    private int id_raccolta;
    private String tipoPianta;
    private int quantita;
    private LocalDate dataRaccolta;
    private String stato;
    private String macchinario;
    private int operatore; //FK di utente
    private int id_piantagione; //FK di piantagione

    public Raccolta(){}

    public Raccolta(int id_raccolta, String tipoPianta, int quantita, LocalDate dataRaccolta, String stato, int operatore, String macchinario, int id_piantagione) {
        this.id_raccolta = id_raccolta;
        this.tipoPianta = tipoPianta;
        this.quantita = quantita;
        this.dataRaccolta = dataRaccolta;
        this.stato = stato;
        this.operatore = operatore;
        this.macchinario = macchinario;
        this.id_piantagione = id_piantagione;
    }

    public int getId_raccolta() {
        return id_raccolta;
    }

    public void setId_raccolta(int id_raccolta) {
        this.id_raccolta = id_raccolta;
    }

    public String getTipoPianta() {
        return tipoPianta;
    }

    public void setTipoPianta(String tipoPianta) {
        this.tipoPianta = tipoPianta;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public LocalDate getDataRaccolta() {
        return dataRaccolta;
    }

    public void setDataRaccolta(LocalDate dataRaccolta) {
        this.dataRaccolta = dataRaccolta;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getOperatore() {
        return operatore;
    }

    public void setOperatore(int operatore) {
        this.operatore = operatore;
    }

    public String getMacchinario() {
        return macchinario;
    }

    public void setMacchinario(String macchinario) {
        this.macchinario = macchinario;
    }

    public int getId_piantagione() {
        return id_piantagione;
    }

    public void setId_piantagione(int id_piantagione) {
        this.id_piantagione = id_piantagione;
    }

 
    @Override
    public String toString() {
        return "Raccolta{id_raccolta=" + id_raccolta + 
        ", tipoPianta='" + tipoPianta + 
        "', quantita=" + quantita + 
        ", dataRaccolta=" + dataRaccolta + 
        ", stato='" + stato + 
        "', macchinario='" + macchinario + 
        "'}";
    }
}
