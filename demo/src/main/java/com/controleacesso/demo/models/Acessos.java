package com.controleacesso.demo.models;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Acessos {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    
    private String tipoAcesso;
    
    private Time horaAcesso;

    @ManyToOne
    @JsonIgnoreProperties("acessos")     
    private Usuarios usuarios;

    @Column(name="data")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dataAcesso;

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
