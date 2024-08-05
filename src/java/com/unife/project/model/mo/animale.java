package SMART_FARM.src.java.com.unife.project.mo;

import java.time.LocalDateTime;

public class animale {
    private int id_animale;
    private int peso;
    private boolean sesso;
    private String razza;
    private String tipoAlimentazione;  //foreign key riferita a Magazzino
    private String nomeStalla;
    private LocalDateTime data_nascita;
    private LocalDateTime data_ingresso;
    private LocalDateTime data_uscita;
    private LocalDateTime data_morte;
    private LocalDateTime data_vaccino;

    //costruttori, getter e setter
    public Animale(int peso, 
            boolean sesso, String razza, String tipoAlimentazione, 
            String nomeStalla, LocalDateTime data_nascita, 
            LocalDateTime data_ingresso, LocalDateTime data_uscita, 
            LocalDateTime data_morte, LocalDateTime data_vaccino) {
                
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
    
    public boolean isSesso() {
        return sesso;
    }
    
    public void setSesso(boolean sesso) {
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
    
    public LocalDateTime getData_nascita() {
        return data_nascita;
    }
    
    public void setData_nascita(LocalDateTime data_nascita) {
        this.data_nascita = data_nascita;
    }
    
    public LocalDateTime getData_ingresso() {
        return data_ingresso;
    }
    
    public void setData_ingresso(LocalDateTime data_ingresso) {
        this.data_ingresso = data_ingresso;
    }
    
    public LocalDateTime getData_uscita() {
        return data_uscita;
    }
    
    public void setData_uscita(LocalDateTime data_uscita) {
        this.data_uscita = data_uscita;
    }
    
    public LocalDateTime getData_morte() {
        return data_morte;
    }
    
    public void setData_morte(LocalDateTime data_morte) {
        this.data_morte = data_morte;
    }
    
    public LocalDateTime getData_vaccino() {
        return data_vaccino;
    }
    
    public void setData_vaccino(LocalDateTime data_vaccino) {
        this.data_vaccino = data_vaccino;
    }
}
