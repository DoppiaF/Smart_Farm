package com.unife.project.model.mo;


public class Piantagione {
    private int id;
    private String tipoPianta;
    private int area;
    private String Stato;
    private int numZone;
    private boolean concimazione;
    private int id_irrigazione; //FK di irrigazione
    private boolean raccolta;

    public Piantagione(int id, String tipoPianta, int area, String stato, int numZone, boolean concimazione, int id_irrigazione, boolean raccolta) {
        this.id = id;
        this.tipoPianta = tipoPianta;
        this.area = area;
        Stato = stato;
        this.numZone = numZone;
        this.concimazione = concimazione;
        this.id_irrigazione = id_irrigazione;
        this.raccolta = raccolta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTipoPianta() {
        return tipoPianta;
    }

    public void setTipoPianta(String tipoPianta) {
        this.tipoPianta = tipoPianta;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getStato() {
        return Stato;
    }

    public void setStato(String stato) {
        Stato = stato;
    }

    public int getNumZone() {
        return numZone;
    }

    public void setNumZone(int numZone) {
        this.numZone = numZone;
    }

    public boolean isConcimazione() {
        return concimazione;
    }   

    public void setConcimazione(boolean concimazione) {
        this.concimazione = concimazione;
    }

    public int getId_irrigazione() {
        return id_irrigazione;
    }

    public void setId_irrigazione(int id_irrigazione) {
        this.id_irrigazione = id_irrigazione;
    }

    public boolean isRaccolta() {
        return raccolta;
    }

    public void setRaccolta(boolean raccolta) {
        this.raccolta = raccolta;
    }




}
