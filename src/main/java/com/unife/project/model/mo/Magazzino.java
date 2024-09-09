package com.unife.project.model.mo;

public class Magazzino {
    private String tipo_magime;
    private int quantità;
    private float prezzo_kg;

    public Magazzino(String tipoMangime, int quantita, float prezzo_kg) {
        this.tipo_magime = tipoMangime;
        this.quantità = quantita;
        this.prezzo_kg = prezzo_kg;
    }

    public String getTipoMangime() {
        return tipo_magime;
    }

    public void setTipoMangime(String tipoMangime) {
        this.tipo_magime = tipoMangime;
    }

    public int getQuantita() {
        return quantità;
    }

    public void setQuantita(int quantita) {
        this.quantità = quantita;
    }

    public float getPrezzo_kg() {
        return prezzo_kg;
    }

    public void setPrezzo_kg(float prezzo_kg) {
        this.prezzo_kg = prezzo_kg;
    }

    @Override
    public String toString() {
        return "Magazzino{tipoMangime='" + tipo_magime + "', quantità=" + quantità + ", prezzo_kg=" + prezzo_kg + '}';
    }

    /*
     * esempio insert stalla
     INSERT INTO magazzino (tipo_mangime,quantità,`prezzo/kg`) VALUES ('granoturco',1000,5.8);
     */
}
