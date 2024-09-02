package com.unife.project.model.mo;

public class Magazzino {
    private String tipoMangime;
    private int quantita;
    private float prezzo_kg;

    public Magazzino(String tipoMangime, int quantita, float prezzo_kg) {
        this.tipoMangime = tipoMangime;
        this.quantita = quantita;
        this.prezzo_kg = prezzo_kg;
    }

    public String getTipoMangime() {
        return tipoMangime;
    }

    public void setTipoMangime(String tipoMangime) {
        this.tipoMangime = tipoMangime;
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

    @Override
    public String toString() {
        return "Magazzino{tipoMangime='" + tipoMangime + "', quantita=" + quantita + ", prezzo_kg=" + prezzo_kg + '}';
    }

    /*
     * esempio insert stalla
     INSERT INTO magazzino (tipo_mangime,quantità,`prezzo/kg`) VALUES ('granoturco',1000,5.8);
     */
}
