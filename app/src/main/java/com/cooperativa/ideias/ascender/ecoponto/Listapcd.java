package com.cooperativa.ideias.ascender.ecoponto;
//Classe para armazenamento de Dados...
public class Listapcd {

    private int id;
    private String title;
    private String shortdesc;
    private double rating;
    private int image;
    private String title2;

    public Listapcd(int id, String title, String shortdesc, double rating, String title2, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.title2 = title2;
        this.image = image;
    }
    //Armazenamento dos atributos da Lista de exibicao em cardview e recycleview...
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public String getTitle2() {
        return title2;

    }

    public int getImage() {
        return image;
    }



}

