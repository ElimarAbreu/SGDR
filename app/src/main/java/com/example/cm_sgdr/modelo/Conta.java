package com.example.cm_sgdr.modelo;

public class Conta {
    private String nome;
    private String responsavel;
    private String codigo_republica;
    private String tipo;
    private String codigo_conta;

    public Conta() {}

    public String getCodigo_conta() {return codigo_conta;}

    public void setCodigo_conta(String codigo_conta) {
        this.codigo_conta = codigo_conta;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getResponsavel() {return responsavel;}

    public void setResponsavel(String responsavel) {this.responsavel = responsavel;}

    public String getCodigo_republica() {return codigo_republica;}

    public void setCodigo_republica(String codigo_republica) {this.codigo_republica = codigo_republica;}

    public String getTipo() {return tipo;}

    public void setTipo(String tipo) {this.tipo = tipo;}
}
