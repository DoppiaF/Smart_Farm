package com.unife.project.model.mo;


public class Piantagione {
    private int id;
    private String tipo_pianta;
    private int area;
    private String stato;
    private int num_zone;
    private boolean concimazione;
    private boolean raccolta;
    private int id_irrigazione; //FK di irrigazione
    

    public Piantagione(int id, String tipo_pianta, int area, String stato, int num_zone, boolean concimazione, int id_irrigazione, boolean raccolta) {
        this.id = id;
        this.tipo_pianta = tipo_pianta;
        this.area = area;
        this.stato = stato;
        this.num_zone = num_zone;
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
        return tipo_pianta;
    }

    public void setTipoPianta(String tipo_pianta) {
        this.tipo_pianta = tipo_pianta;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getNumZone() {
        return num_zone;
    }

    public void setNumZone(int num_zone) {
        this.num_zone = num_zone;
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


    @Override
    public String toString() {
        return "Piantagione{id=" + id + 
            ", tipoPianta='" + tipo_pianta + 
            "', area=" + area + 
            ", Stato='" + stato + 
            "', numZone=" + num_zone + 
            ", concimazione=" + concimazione +
            ", raccolta=" + raccolta 
            + '}';
    }

}
