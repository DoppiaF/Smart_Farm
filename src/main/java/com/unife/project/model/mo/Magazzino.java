package com.unife.project.model.mo;

import java.time.LocalDate;

public class Magazzino {
    private int id;
    private String magime;
    private int quantita;
    private float prezzo_kg;
    private LocalDate data;

    public Magazzino() {
    }

    public Magazzino(String tipoMangime, int quantita, float prezzo_kg, LocalDate data) {
        this.magime = tipoMangime;
        this.quantita = quantita;
        this.prezzo_kg = prezzo_kg;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTipoMangime() {
        return magime;
    }

    public void setTipoMangime(String tipoMangime) {
        this.magime = tipoMangime;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public float getPrezzo_kg() {
        return prezzo_kg;
    }

    public void setPrezzo_kg(float prezzo_kg) {
        this.prezzo_kg = prezzo_kg;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Magazzino{id='" + id + " tipoMangime='" + magime + "', quantita=" + quantita + ", prezzo_kg=" + prezzo_kg + ", data=" + data + '}';
    }

    /*
     * esempio insert stalla
     INSERT INTO magazzino (tipo_mangime,quantita,`prezzo/kg`) VALUES ('granoturco',1000,5.8);
     */
}
