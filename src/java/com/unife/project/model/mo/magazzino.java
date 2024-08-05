package SMART_FARM.src.java.com.unife.project.mo;

public class magazzino {
    private String tipoMangime;
    private int quantita;
    private float prezzo_kg;

    public magazzino(String tipoMangime, int quantita, float prezzo_kg) {
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

    
}
