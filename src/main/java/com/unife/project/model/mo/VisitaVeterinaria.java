package com.unife.project.model.mo;

import java.time.LocalDate;

public class VisitaVeterinaria {
    private LocalDate data;     //PK
    private String diagnosi;
    private int identificativo_animale; //FK di animale  //PK
    private String cura_prescritta;
    private String nome_veterinario;
    private String cognome_veterinario;
    private String stato_animale;
    private boolean programmata;
    

    public VisitaVeterinaria(LocalDate data, String diagnosi, int identificativoAnimale, String nomeVeterinario, String cognomeVeterinario, String curaPrescritta, String stato_animale, boolean programmata) {
        this.data = data;
        this.diagnosi = diagnosi;
        this.identificativo_animale = identificativoAnimale;
        this.nome_veterinario = nomeVeterinario;
        this.cognome_veterinario = cognomeVeterinario;
        this.cura_prescritta = curaPrescritta;
        this.stato_animale = stato_animale;
        this.programmata = programmata;
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
        return identificativo_animale;
    }

    public void setIdentificativoAnimale(int identificativoAnimale) {
        this.identificativo_animale = identificativoAnimale;
    }

    public String getNomeVeterinario() {
        return nome_veterinario;
    }

    public void setNomeVeterinario(String nomeVeterinario) {
        this.nome_veterinario = nomeVeterinario;
    }

    public String getCognomeVeterinario() {
        return cognome_veterinario;
    }

    public void setCognomeVeterinario(String cognomeVeterinario) {
        this.cognome_veterinario = cognomeVeterinario;
    }

    public String getCuraPrescritta() {
        return cura_prescritta;
    }

    public void setCuraPrescritta(String curaPrescritta) {
        this.cura_prescritta = curaPrescritta;
    }

    public String getStatoAnimale(){
        return stato_animale;
    }

    public void setStatoAnimale(String stato_animale){
        this.stato_animale = stato_animale;
    }

    public boolean getProgrammata(){
        return programmata;
    }

    public void setProgrammata(boolean programmata){
        this.programmata = programmata;
    }


    @Override
    public String toString() {
        return "VisitaVeterinaria{" +
                "data=" + data +
                ", diagnosi='" + diagnosi + '\'' +
                ", identificativoAnimale=" + identificativo_animale +
                ", nomeVeterinario='" + nome_veterinario + '\'' +
                ", cognomeVeterinario='" + cognome_veterinario + '\'' +
                ", curaPrescritta='" + cura_prescritta + '\'' +
                ", stato_animale='" + stato_animale + '\'' +
                ", programmata=" + programmata +
                '}';
    }
}
