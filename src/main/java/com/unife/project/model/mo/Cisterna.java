package com.unife.project.model.mo;

import java.util.ArrayList;
import java.util.List;

public class Cisterna {
    private int id;
    private int capacita;
    private int quantita;
    private List<Irrigazione> irrigazioni = new ArrayList<>(); // Lista di irrigazioni associate

    public Cisterna(int capacita, int quantita) {
        this.capacita = capacita;   // id non richiesto perchè lo assegna il db con autoincremento
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

    public List<Irrigazione> getIrrigazioni() {
        return irrigazioni;
    }

    public void setIrrigazioni(List<Irrigazione> irrigazioni) {
        this.irrigazioni = irrigazioni;
    }

    @Override
    public String toString() {
        return "Cisterna{" +
                "id=" + id +
                ", capacita=" + capacita +
                ", quantita=" + quantita +
                ", irrigazioni=" + irrigazioni +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cisterna cisterna = (Cisterna) o;

        if (id != cisterna.id) return false;
        if (capacita != cisterna.capacita) return false;
        if (quantita != cisterna.quantita) return false;
        return irrigazioni != null ? irrigazioni.equals(cisterna.irrigazioni) : cisterna.irrigazioni == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + capacita;
        result = 31 * result + quantita;
        result = 31 * result + (irrigazioni != null ? irrigazioni.hashCode() : 0);
        return result;
    }
}