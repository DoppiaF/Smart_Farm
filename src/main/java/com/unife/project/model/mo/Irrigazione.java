package com.unife.project.model.mo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Irrigazione {
    private int id_irrigazione;
    private LocalDate data_inizio;
    private LocalTime ora_inizio;
    private int durata;
    private boolean auto;
    private String stato;
    private int litri_usati;
    private List<Cisterna> cisterne = new ArrayList<>(); //lista cisterne associate (relazione N:M)

    //costruttore vuoto per il dao
    public Irrigazione() {
    }

    public Irrigazione(
            LocalDate data_inizio,
            LocalTime ora_inizio, 
            int durata, 
            boolean auto, 
            String stato, 
            int litri_usati) {

        this.data_inizio = data_inizio;
        this.ora_inizio = ora_inizio;
        this.durata = durata;
        this.auto = auto;
        this.stato = stato;
        this.litri_usati = litri_usati;
    }

    public int getId_irrigazione() {
        return id_irrigazione;
    }

    public void setId_irrigazione(int id_irrigazione) {
        this.id_irrigazione = id_irrigazione;
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public LocalTime getOra_inizio() {
        return ora_inizio;
    }

    public void setOra_inizio(LocalTime ora_inizio) {
        this.ora_inizio = ora_inizio;
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

    @Override
    public String toString() {
        return "Irrigazione{" +
                "id_irrigazione=" + id_irrigazione +
                ", data_inizio=" + data_inizio +
                ", ora_inizio=" + ora_inizio +
                ", durata=" + durata +
                ", auto=" + auto +
                ", stato='" + stato + '\'' +
                ", litri_usati=" + litri_usati +
                '}';
    }
    

}
