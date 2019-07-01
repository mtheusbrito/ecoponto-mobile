package com.cooperativa.ideias.ascender.ecoponto.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class Estado  implements Serializable, Parcelable, Comparable<Estado> {
    private int id;
    private String sigla;
    private String nome;


    protected Estado(Parcel in) {
        id = in.readInt();
        sigla = in.readString();
        nome = in.readString();
    }

    public static final Creator<Estado> CREATOR = new Creator<Estado>() {
        @Override
        public Estado createFromParcel(Parcel in) {
            return new Estado(in);
        }

        @Override
        public Estado[] newArray(int size) {
            return new Estado[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(sigla);
        dest.writeString(nome);
    }

    public Estado(int id, String sigla, String nome) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;


    }

    public Estado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return nome;
    }


    @Override
    public int compareTo(Estado o) {
        if (this.getId() < o.getId()) {
            return -1;
        }
        if (this.getId() > o.getId()) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Estado)) {
            return false;
        }

        Estado converted = (Estado)obj;

        return this.getId() == (converted.getId());
    }
}
