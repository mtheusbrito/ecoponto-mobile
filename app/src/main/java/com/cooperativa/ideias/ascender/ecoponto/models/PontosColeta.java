package com.cooperativa.ideias.ascender.ecoponto.models;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class PontosColeta implements Serializable {

    private String titulo, snippet;
    private LatLng latLng;

    public PontosColeta(String titulo, String snippet, LatLng latLng) {
        this.titulo = titulo;
        this.snippet = snippet;
        this.latLng = latLng;
    }

    public PontosColeta() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
