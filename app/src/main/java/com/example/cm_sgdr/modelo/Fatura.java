package com.example.cm_sgdr.modelo;

public class Fatura {
    private String mes;
    private String multa;
    private String total;
    private String data;
    private String c_conta;

    public Fatura() {
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getC_conta() {
        return c_conta;
    }

    public void setC_conta(String c_conta) {
        this.c_conta = c_conta;
    }
}
