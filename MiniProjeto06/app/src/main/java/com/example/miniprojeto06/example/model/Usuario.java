package com.example.miniprojeto06.example.model;

public class Usuario {
    private String nome;
    private String telefone;
    private int id;
    private int img;

    public Usuario(int id, String nome, String telefone, int img){
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
