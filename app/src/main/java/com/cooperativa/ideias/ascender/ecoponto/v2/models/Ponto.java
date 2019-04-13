package com.cooperativa.ideias.ascender.ecoponto.v2.models;

import java.io.Serializable;

public class Ponto implements Serializable {

    public String id;
    public String local;
    public String inicio;
    public String termino;
    public String url;
    public String latitude;
    public String longitude;

    public Ponto() {
    }

    public Ponto(String id, String local, String inicio, String termino, String url, String latitude, String longitude) {
        this.id = id;
        this.local = local;
        this.inicio = inicio;
        this.termino = termino;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
