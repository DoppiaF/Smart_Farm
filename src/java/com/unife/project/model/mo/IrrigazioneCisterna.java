package com.unife.project.model.entity;

public class IrrigazioneCisterna {
    private int id_impostazione_irrigazione;
    private int id_cisterna;

    // Costruttori, getter e setter

    public IrrigazioneCisterna() {}

    public IrrigazioneCisterna(int id_impostazione_irrigazione, int id_cisterna) {
        this.id_impostazione_irrigazione = id_impostazione_irrigazione;
        this.id_cisterna = id_cisterna;
    }

    // Getters and setters

    public int getId_impostazione_irrigazione() {
        return id_impostazione_irrigazione;
    }

    public void setId_impostazione_irrigazione(int id_impostazione_irrigazione) {
        this.id_impostazione_irrigazione = id_impostazione_irrigazione;
    }

    public int getId_cisterna() {
        return id_cisterna;
    }

    public void setId_cisterna(int id_cisterna) {
        this.id_cisterna = id_cisterna;
    }
}
