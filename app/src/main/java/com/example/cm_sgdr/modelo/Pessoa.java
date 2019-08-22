package com.example.cm_sgdr.modelo;

public class Pessoa {
    //private String uid;
    private String nome;
    private String email;
    private String senha;
    private String user;
    private String cod_republica;
    private String cpf;
    private String banco_nome;
    private String banco_agencia;
    private String banco_conta;
    private String banco_operacao;



    public Pessoa() {

    }

    /*public String getUid() { return uid; }*/

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public String getSenha() { return senha; }

    public String getUser() { return user; }

    public String getCod_republica() { return cod_republica; }

    public String getCpf() { return cpf; }

    public String getBanco_nome() { return banco_nome; }

    public String getBanco_agencia() { return banco_agencia; }

    public String getBanco_conta() { return banco_conta; }

    public String getBanco_operacao() { return banco_operacao; }

    public void setBanco_operacao(String banco_operacao) { this.banco_operacao = banco_operacao; }

    public void setBanco_conta(String banco_conta) { this.banco_conta = banco_conta; }

    public void setBanco_agencia(String banco_agencia) { this.banco_agencia = banco_agencia; }

    public void setBanco_nome(String banco_nome) { this.banco_nome = banco_nome; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public void setCod_republica(String cod_republica) { this.cod_republica = cod_republica; }

    public void setUser(String user) { this.user = user; }

    public void setSenha(String senha) { this.senha = senha; }

    /*public void setUid(String uid) { this.uid = uid; }*/

    public void setNome(String nome) { this.nome = nome; }

    public void setEmail(String email) { this.email = email; }
}

