package com.cooperativa.ideias.ascender.ecoponto.models;

public class Parceiro {

   private String id, nome, descricao, url;


    public Parceiro(String id, String nome, String descricao, String url) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.url = url;
    }
    public Parceiro(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
