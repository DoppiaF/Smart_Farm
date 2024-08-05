package com.unife.project.model.mo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class irrigazione {
    private int id_impostazione;
    private LocalDateTime data_inizio;
    private int durata;
    private boolean auto;
    private String stato;
    private int litri_usati;
    private List<Cisterna> cisterne = new ArrayList<>(); //lista cisterne associate (relazione N:M)

    public irrigazione(
            LocalDateTime data_inizio, 
            int durata, 
            boolean auto, 
            String stato, 
            int litri_usati) {

        this.data_inizio = data_inizio;
        this.durata = durata;
        this.auto = auto;
        this.stato = stato;
        this.litri_usati = litri_usati;
    }

    public int getId_impostazione() {
        return id_impostazione;
    }

    public void setId_impostazione(int id_impostazione) {
        this.id_impostazione = id_impostazione;
    }

    public LocalDateTime getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDateTime data_inizio) {
        this.data_inizio = data_inizio;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public boolean isAuto() {
        return auto;
    }   

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getLitri_usati() {
        return litri_usati;
    }

    public void setLitri_usati(int litri_usati) {
        this.litri_usati = litri_usati;
    }

    public List<Cisterna> getCisterne() {
        return cisterne;
    }

    public void setCisterne(List<Cisterna> cisterne) {
        this.cisterne = cisterne;
    }
}
