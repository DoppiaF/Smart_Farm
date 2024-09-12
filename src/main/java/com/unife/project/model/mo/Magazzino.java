package com.unife.project.model.mo;

import java.time.LocalDate;

public class Magazzino {
    private String tipo_magime;
    private int quantita;
    private float prezzo_kg;
    private LocalDate data;

    public Magazzino() {
    }

    public Magazzino(String tipoMangime, int quantita, float prezzo_kg, LocalDate data) {
        this.tipo_magime = tipoMangime;
        this.quantita = quantita;
        this.prezzo_kg = prezzo_kg;
        this.data = data;
    }

    public String getTipoMangime() {
        return tipo_magime;
    }

    public void setTipoMangime(String tipoMangime) {
        this.tipo_magime = tipoMangime;
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
        return "Magazzino{tipoMangime='" + tipo_magime + "', quantita=" + quantita + ", prezzo_kg=" + prezzo_kg + ", data=" + data + '}';
    }

    /*
     * esempio insert stalla
     INSERT INTO magazzino (tipo_mangime,quantita,`prezzo/kg`) VALUES ('granoturco',1000,5.8);
     */
}
