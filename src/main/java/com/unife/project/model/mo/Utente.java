package com.unife.project.model.mo;

import java.time.LocalDateTime;

public class Utente {
    private int id;
    private String userName;
    private String password;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime dataNascita;
    private Boolean ruolo_raccolta;
    private Boolean ruolo_irrigazione;
    private Boolean ruolo_pastore;
    private Boolean ruolo_admin;

    public Utente(int id, String userName, String password, String email, LocalDateTime createTime, LocalDateTime dataNascita, Boolean ruolo_raccolta, Boolean ruolo_irrigazione, Boolean ruolo_pastore, Boolean ruolo_admin) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createTime = createTime;
        this.dataNascita = dataNascita;
        this.ruolo_raccolta = ruolo_raccolta;
        this.ruolo_irrigazione = ruolo_irrigazione;
        this.ruolo_pastore = ruolo_pastore;
        this.ruolo_admin = ruolo_admin;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    
    public LocalDateTime getDataNascita() {
        return dataNascita;
    }
    
    public void setDataNascita(LocalDateTime dataNascita) {
        this.dataNascita = dataNascita;
    }
    
    public Boolean getRuolo_raccolta() {
        return ruolo_raccolta;
    }
    
    public void setRuolo_raccolta(Boolean ruolo_raccolta) {
        this.ruolo_raccolta = ruolo_raccolta;
    }
    
    public Boolean getRuolo_irrigazione() {
        return ruolo_irrigazione;
    }
    
    public void setRuolo_irrigazione(Boolean ruolo_irrigazione) {
        this.ruolo_irrigazione = ruolo_irrigazione;
    }
    
    public Boolean getRuolo_pastore() {
        return ruolo_pastore;
    }
    
    public void setRuolo_pastore(Boolean ruolo_pastore) {
        this.ruolo_pastore = ruolo_pastore;
    }
    
    public Boolean getRuolo_admin() {
        return ruolo_admin;
    }
    
    public void setRuolo_admin(Boolean ruolo_admin) {
        this.ruolo_admin = ruolo_admin;
    }

    @Override
    public String toString() {
        return "Utente{id=" + id + 
               ", userName='" + userName + '\'' + 
               ", email='" + email + '\'' + 
               ", createTime=" + createTime + 
               ", dataNascita=" + dataNascita + 
               ", ruolo_raccolta=" + ruolo_raccolta + 
               ", ruolo_irrigazione=" + ruolo_irrigazione + 
               ", ruolo_pastore=" + ruolo_pastore + 
               ", ruolo_admin=" + ruolo_admin + 
               '}';
    }
}
