package com.controleacesso.demo.models;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Acessos {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    
    private String tipoAcesso;
    
    @Temporal(TemporalType.TIME)
    private Time horaAcesso = new Time(System.currentTimeMillis());

    @ManyToOne
    @JsonIgnoreProperties("acessos")     
    private Usuarios usuarios;
   
    @JsonFormat(pattern="dd-MM-yyyy") @Temporal(TemporalType.DATE)
    private Date dataAcesso = new Date(System.currentTimeMillis());

    public Acessos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(String tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public Time getHoraAcesso() {
        return horaAcesso;
    }

    public void setHoraAcesso(Time horaAcesso) {
        this.horaAcesso = horaAcesso;
    }

    public Date getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(Date dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }    
    
}
