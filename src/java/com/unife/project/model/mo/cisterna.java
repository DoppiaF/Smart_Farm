package SMART_FARM.src.java.com.unife.project.mo;

import java.util.ArrayList;
import java.util.List;

public class cisterna {
    private int id;
    private int capacita;
    private int quantita;
    private List<Irrigazione> irrigazioni = new ArrayList<>(); // Lista di irrigazioni associate


    public Cisterna(int capacita, int quantita) {
        this.capacita = capacita;   //id non richiesto perchè lo assegna il db con autoincremento
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacita() {
        return capacita;
    }

    public void setCapacita(int capacita) {
        this.capacita = capacita;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public List<Irrigazione> getIrrigazioni() {     //getter e setter per la lista di irrigazioni associate, relazione N:M
        return irrigazioni;
    }

    public void setIrrigazioni(List<Irrigazione> irrigazioni) {
        this.irrigazioni = irrigazioni;
    }
}
