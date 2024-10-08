package com.unife.project.model.mo;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Utente {
    private int id;
    private String username;
    private String password;
    private String email;
    private Timestamp create_time;   //data di creazione dell'utente, riempita automaticamente dal database
    private LocalDate data_nascita;
    private Boolean ruolo_raccolta;
    private Boolean ruolo_irrigazione;
    private Boolean ruolo_pastore;
    private Boolean ruolo_admin;


    //costruttore vuoto
    public Utente() {
    }

    public Utente(int id, String username, String password, String email, Timestamp create_time, LocalDate data_nascita, Boolean ruolo_raccolta, Boolean ruolo_irrigazione, Boolean ruolo_pastore, Boolean ruolo_admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.create_time = create_time;
        this.data_nascita = data_nascita;
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
        return username;
    }
    
    public void setUserName(String userName) {
        this.username = userName;
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
    
    public Timestamp getCreateTime() {
        return create_time;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.create_time = createTime;
    }
    
    public LocalDate getDataNascita() {
        return data_nascita;
    }
    
    public void setDataNascita(LocalDate dataNascita) {
        this.data_nascita = dataNascita;
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
               ", userName='" + username + '\'' + 
               ", email='" + email + '\'' + 
               ", createTime=" + create_time + 
               ", dataNascita=" + data_nascita + 
               ", ruolo_raccolta=" + ruolo_raccolta + 
               ", ruolo_irrigazione=" + ruolo_irrigazione + 
               ", ruolo_pastore=" + ruolo_pastore + 
               ", ruolo_admin=" + ruolo_admin + 
               ", password='" + password + '\'' +
               '}';
    }

    /*
     INSERT INTO user (username, email, password, data_nascita, ruolo_raccolta, ruolo_irrigazione, ruolo_pastore, ruolo_admin) VALUES ('Ale', 'ale@gmail.com', 'AZ', '1997-03-13',0,0,0,1);
     */
}
