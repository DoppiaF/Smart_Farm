package com.unife.project.model.mo;

public class Zona {
    private int coordX; //PK
    private int coordY; //PK
    private int portataSensore;
    private String statoTerreno;    //stato generale del terreno
    private float sensoreIluminazione;
    private float sensoreUmidita;
    private float sensoreTemperatura;
    private float sensorePH;
    private float sensoreVento;
    private int id_piantagione; //FK di piantagione

    public Zona(int coordX, int coordY, int portataSensore, String statoTerreno, float sensoreIluminazione, float sensoreUmidita, float sensoreTemperatura, float sensorePH, float sensoreVento, int id_piantagione) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.portataSensore = portataSensore;
        this.statoTerreno = statoTerreno;
        this.sensoreIluminazione = sensoreIluminazione;
        this.sensoreUmidita = sensoreUmidita;
        this.sensoreTemperatura = sensoreTemperatura;
        this.sensorePH = sensorePH;
        this.sensoreVento = sensoreVento;
        this.id_piantagione = id_piantagione;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getPortataSensore() {
        return portataSensore;
    }

    public void setPortataSensore(int portataSensore) {
        this.portataSensore = portataSensore;
    }

    public String getStatoTerreno() {
        return statoTerreno;
    }

    public void setStatoTerreno(String statoTerreno) {
        this.statoTerreno = statoTerreno;
    }

    public float getSensoreIluminazione() {
        return sensoreIluminazione;
    }

    public void setSensoreIluminazione(float sensoreIluminazione) {
        this.sensoreIluminazione = sensoreIluminazione;
    }

    public float getSensoreUmidita() {
        return sensoreUmidita;
    }

    public void setSensoreUmidita(float sensoreUmidita) {
        this.sensoreUmidita = sensoreUmidita;
    }

    public float getSensoreTemperatura() {
        return sensoreTemperatura;
    }

    public void setSensoreTemperatura(float sensoreTemperatura) {
        this.sensoreTemperatura = sensoreTemperatura;
    }

    public float getSensorePH() {
        return sensorePH;
    }

    public void setSensorePH(float sensorePH) {
        this.sensorePH = sensorePH;
    }

    public float getSensoreVento() {
        return sensoreVento;
    }

    public void setSensoreVento(float sensoreVento) {
        this.sensoreVento = sensoreVento;
    }

    public int getId_piantagione() {
        return id_piantagione;
    }

    public void setId_piantagione(int id_piantagione) {
        this.id_piantagione = id_piantagione;
    }

    @Override
    public String toString() {
        return "Zona{" +
                "coordX=" + coordX +
                ", coordY=" + coordY +
                ", portataSensore=" + portataSensore +
                ", statoTerreno='" + statoTerreno + '\'' +
                ", sensoreIluminazione=" + sensoreIluminazione +
                ", sensoreUmidita=" + sensoreUmidita +
                ", sensoreTemperatura=" + sensoreTemperatura +
                ", sensorePH=" + sensorePH +
                ", sensoreVento=" + sensoreVento +
                '}';
    }

}
