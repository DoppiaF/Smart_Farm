package com.unife.project.model.mo;

public class IrrigazioneCisterna {
    private int id_irrigazione;
    private int id_cisterna;

    // Costruttori, getter e setter

    public IrrigazioneCisterna() {}

    public IrrigazioneCisterna(int id_irrigazione, int id_cisterna) {
        this.id_irrigazione = id_irrigazione;
        this.id_cisterna = id_cisterna;
    }

    // Getters and setters

    public int getId_irrigazione() {
        return id_irrigazione;
    }

    public void setId_irrigazione(int id_irrigazione) {
        this.id_irrigazione = id_irrigazione;
    }

    public int getId_cisterna() {
        return id_cisterna;
    }

    public void setId_cisterna(int id_cisterna) {
        this.id_cisterna = id_cisterna;
    }
}
