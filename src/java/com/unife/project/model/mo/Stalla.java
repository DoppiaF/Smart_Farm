package com.unife.project.model.mo;

public class Stalla {
    private String etichettaStalla;
    private int capienza;
    private String razza;
    private LocalDateTime oraPranzo;
    private LocalDateTime oraCena;

    public Stalla(String etichettaStalla, int capienza, String razza, LocalDateTime oraPranzo, LocalDateTime oraCena) {
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

    public LocalDateTime getOraPranzo() {
        return oraPranzo;
    }

    public void setOraPranzo(LocalDateTime oraPranzo) {
        this.oraPranzo = oraPranzo;
    }

    public LocalDateTime getOraCena() {
        return oraCena;
    }

    public void setOraCena(LocalDateTime oraCena) {
        this.oraCena = oraCena;
    }




}
