package com.unife.project.model.mo;

import java.time.LocalDate;

public class RaccoltaPerAnno {
    private int anno;
    private int totale_quantita;

    public RaccoltaPerAnno(int anno, int totale_quantita) {
        this.anno = anno;
        this.totale_quantita = totale_quantita;
    }

    public int getDataRaccolta() {
        return anno;
    }

    public void setDataRaccolta(int dataRaccolta) {
        this.anno = dataRaccolta;
    }

    public int getQuantita() {
        return totale_quantita;
    }

    public void setQuantita(int quantita) {
        this.totale_quantita = quantita;
    }

    @Override
    public String toString() {
        return "RaccoltaPerAnno{" +
                "dataRaccolta=" + anno +
                ", quantita=" + totale_quantita +
                '}';
    }
}
