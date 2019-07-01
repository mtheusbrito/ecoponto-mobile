package com.cooperativa.ideias.ascender.ecoponto.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Cidade implements Serializable, Parcelable {
    private int id ;
    private String nome;
    private String latitude;
    private String longitude;
    private Estado estado;


    protected Cidade(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        latitude = in.readString();
        longitude = in.readString();

    }

    public static final Creator<Cidade> CREATOR = new Creator<Cidade>() {
        @Override
        public Cidade createFromParcel(Parcel in) {
            return new Cidade(in);
        }

        @Override
        public Cidade[] newArray(int size) {
            return new Cidade[size];
        }
    };

    @Override
    public String toString() {
        return getNome();
    }

    public Cidade(){

    }
    public Cidade(int id, String nome, String latitude, String longitude, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(latitude);
        dest.writeString(longitude);


    }


}
