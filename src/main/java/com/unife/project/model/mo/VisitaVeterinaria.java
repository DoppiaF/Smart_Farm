package com.unife.project.model.mo;

import java.time.LocalDate;

public class VisitaVeterinaria {
    private LocalDate data;     //PK
    private String diagnosi;
    private int identificativoAnimale; //FK di animale  //PK
    private LocalDate prossimaVisita;
    private String nomeVeterinario;
    private String cognomeVeterinario;
    private String curaPrescritta;

    public VisitaVeterinaria(LocalDate data, String diagnosi, int identificativoAnimale, LocalDate prossimaVisita, String nomeVeterinario, String cognomeVeterinario, String curaPrescritta) {
        this.data = data;
        this.diagnosi = diagnosi;
        this.identificativoAnimale = identificativoAnimale;
        this.prossimaVisita = prossimaVisita;
        this.nomeVeterinario = nomeVeterinario;
        this.cognomeVeterinario = cognomeVeterinario;
        this.curaPrescritta = curaPrescritta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDiagnosi() {
        return diagnosi;
    }

    public void setDiagnosi(String diagnosi) {
        this.diagnosi = diagnosi;
    }

    public int getIdentificativoAnimale() {
        return identificativoAnimale;
    }

    public void setIdentificativoAnimale(int identificativoAnimale) {
        this.identificativoAnimale = identificativoAnimale;
    }

    public LocalDate getProssimaVisita() {
        return prossimaVisita;
    }

    public void setProssimaVisita(LocalDate prossimaVisita) {
        this.prossimaVisita = prossimaVisita;
    }

    public String getNomeVeterinario() {
        return nomeVeterinario;
    }

    public void setNomeVeterinario(String nomeVeterinario) {
        this.nomeVeterinario = nomeVeterinario;
    }

    public String getCognomeVeterinario() {
        return cognomeVeterinario;
    }

    public void setCognomeVeterinario(String cognomeVeterinario) {
        this.cognomeVeterinario = cognomeVeterinario;
    }

    public String getCuraPrescritta() {
        return curaPrescritta;
    }

    public void setCuraPrescritta(String curaPrescritta) {
        this.curaPrescritta = curaPrescritta;
    }


    @Override
    public String toString() {
        return "VisitaVeterinaria{" +
                "data=" + data +
                ", diagnosi='" + diagnosi + '\'' +
                ", identificativoAnimale=" + identificativoAnimale +
                ", prossimaVisita=" + prossimaVisita +
                ", nomeVeterinario='" + nomeVeterinario + '\'' +
                ", cognomeVeterinario='" + cognomeVeterinario + '\'' +
                ", curaPrescritta='" + curaPrescritta + '\'' +
                '}';
    }
}
