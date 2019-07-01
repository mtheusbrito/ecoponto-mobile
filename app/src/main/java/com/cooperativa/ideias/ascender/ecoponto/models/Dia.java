package com.cooperativa.ideias.ascender.ecoponto.models;

import java.io.Serializable;

public class Dia implements Serializable,Comparable<Dia> {
    private String id;
    private String label;
    private Integer value;

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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Dia() {
    }

    public Dia(String id, String label, Integer value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }



    @Override
    public int compareTo(Dia o) {
        if (this.value < o.value) {
            return -1;
        }
        if (this.value > o.value) {
            return 1;
        }
        return 0;
    }
}
