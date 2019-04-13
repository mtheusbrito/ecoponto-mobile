package com.cooperativa.ideias.ascender.ecoponto.v2.models;

import java.io.Serializable;

public class Dia implements Serializable {
public String id;
public String label;
public String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Dia() {
    }

    public Dia(String id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }
}
