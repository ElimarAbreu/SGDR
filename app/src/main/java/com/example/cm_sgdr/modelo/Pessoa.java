package com.example.cm_sgdr.modelo;

public class Pessoa {
    private String uid;
    private String nome;
    private String email;
    private String senha;


    public Pessoa() {

    }

    public String getUid() {
        return uid;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }


    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome;
    }
}

