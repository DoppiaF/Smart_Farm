package com.unife.project.model.mo;

import java.time.LocalTime;

public class Stalla {
    private String etichettaStalla;
    private int capienza;
    private String razza;
    private LocalTime oraPranzo;
    private LocalTime oraCena;

    public Stalla(String etichettaStalla, int capienza, String razza, LocalTime oraPranzo, LocalTime oraCena) {
        this.etichettaStalla = etichettaStalla;
        this.capienza = capienza;
        this.razza = razza;
        this.oraPranzo = oraPranzo;
        this.oraCena = oraCena;
    }

    public String getEtichettaStalla() {
        return etichettaStalla;
    }

    public void setEtichettaStalla(String etichettaStalla) {
        this.etichettaStalla = etichettaStalla;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public String getRazza() {
        return razza;
    }   

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public LocalTime getOraPranzo() {
        return oraPranzo;
    }

    public void setOraPranzo(LocalTime oraPranzo) {
        this.oraPranzo = oraPranzo;
    }

    public LocalTime getOraCena() {
        return oraCena;
    }

    public void setOraCena(LocalTime oraCena) {
        this.oraCena = oraCena;
    }


    @Override
    public String toString() {
        return "Stalla{etichettaStalla='" + etichettaStalla + "', capienza=" + capienza + ", razza='" + razza + "', oraPranzo=" + oraPranzo + ", oraCena=" + oraCena + '}';
    }


    /*
     esempio insert per stalla
     INSERT INTO stalla (etichetta_stalla, capienza, razza, ora_pranzo, ora_cena) VALUES ('stalla_A', 500, 'frisona', '10:00:00', '22:00:00');
    */
}
