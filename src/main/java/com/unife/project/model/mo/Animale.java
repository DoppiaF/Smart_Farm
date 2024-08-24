package com.unife.project.model.mo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Animale {
    private int id_animale;
    private int peso;
    private char sesso;
    private String razza;
    private String tipoAlimentazione;  //foreign key riferita a Magazzino
    private String nomeStalla;
    private LocalDate data_nascita;
    private LocalDate data_ingresso;
    private LocalDate data_uscita;
    private LocalDate data_morte;
    private LocalDate data_vaccino;
    private List<Prodotto> prodotti = new ArrayList<>();    //foreign key riferita a lista prodotti

    //costruttori, getter e setter
    public Animale() {
    }

    public Animale(int peso, 
            char sesso, String razza, String tipoAlimentazione, 
            String nomeStalla, LocalDate data_nascita, 
            LocalDate data_ingresso, LocalDate data_uscita, 
            LocalDate data_morte, LocalDate data_vaccino) {
                
        this.peso = peso;
        this.sesso = sesso;
        this.razza = razza;
        this.tipoAlimentazione = tipoAlimentazione;
        this.nomeStalla = nomeStalla;
        this.data_nascita = data_nascita;
        this.data_ingresso = data_ingresso;
        this.data_uscita = data_uscita;
        this.data_morte = data_morte;
        this.data_vaccino = data_vaccino;
    }

    public int getId_animale() {
        return id_animale;
    }
    
    public void setId_animale(int id_animale) {
        this.id_animale = id_animale;
    }
    
    public int getPeso() {
        return peso;
    }
    
    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    public char getSesso() {
        return sesso;
    }
    
    public void setSesso(char sesso) {
        this.sesso = sesso;
    }
    
    public String getRazza() {
        return razza;
    }
    
    public void setRazza(String razza) {
        this.razza = razza;
    }
    
    public String getTipoAlimentazione() {
        return tipoAlimentazione;
    }
    
    public void setTipoAlimentazione(String tipoAlimentazione) {
        this.tipoAlimentazione = tipoAlimentazione;
    }
    
    public String getNomeStalla() {
        return nomeStalla;
    }
    
    public void setNomeStalla(String nomeStalla) {
        this.nomeStalla = nomeStalla;
    }
    
    public LocalDate getData_nascita() {
        return data_nascita;
    }
    
    public void setData_nascita(LocalDate data_nascita) {
        this.data_nascita = data_nascita;
    }
    
    public LocalDate getData_ingresso() {
        return data_ingresso;
    }
    
    public void setData_ingresso(LocalDate data_ingresso) {
        this.data_ingresso = data_ingresso;
    }
    
    public LocalDate getData_uscita() {
        return data_uscita;
    }
    
    public void setData_uscita(LocalDate data_uscita) {
        this.data_uscita = data_uscita;
    }
    
    public LocalDate getData_morte() {
        return data_morte;
    }
    
    public void setData_morte(LocalDate data_morte) {
        this.data_morte = data_morte;
    }
    
    public LocalDate getData_vaccino() {
        return data_vaccino;
    }
    
    public void setData_vaccino(LocalDate data_vaccino) {
        this.data_vaccino = data_vaccino;
    }


    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    @Override
    public String toString() {
        return "Animale{" +
                "id=" + id_animale +
                ", peso=" + peso +
                ", razza='" + razza + '\'' +
                ", tipoAlimentazione=" + tipoAlimentazione + '\'' +
                ", nomeStalla='" + nomeStalla + '\'' +
                ", data_nascita=" + data_nascita + '\'' +
                ", data_ingresso=" + data_ingresso + '\'' +
                ", data_uscita=" + data_uscita + '\'' +
                ", data_morte=" + data_morte + '\'' +
                ", data_vaccino=" + data_vaccino + '\'' +
                '}';
    }
}