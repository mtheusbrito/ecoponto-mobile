package com.cooperativa.ideias.ascender.ecoponto.models;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

public class Coleta implements Serializable {
    private String id;
    private String dia;
    private String horario;
    private Integer qt;
    private String local;



    public Coleta(){
    this.id = UUID.randomUUID().toString();
    }


    public Coleta(String id, String dia, String horario, Integer qt, String local) {
        this.id = id;
        this.dia = dia;
        this.horario = horario;
        this.qt = qt;
        this.local = local;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getQt() {
        return qt;
    }

    public void setQt(Integer qt) {
        this.qt = qt;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
