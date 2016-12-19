package com.joaohenrique.entregas.fornecedores.modelo;

import com.joaohenrique.entregas.fornecedores.webservice.handlers.ErroRecurso;

import java.math.BigDecimal;

public class Fornecedor extends ErroRecurso {

    private Integer codFornecedor;
    private String nome;
    private String cnpj;
    private String email;
    private String fone;
    private BigDecimal faturamentoAnual;
    private String dataCadastro;


    public Fornecedor() {
    }

    public Fornecedor(String nome, String email, String cnpj, String fone, BigDecimal faturamentoAnual, String dataCadastro) {

        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
        this.fone = fone;
        this.faturamentoAnual = faturamentoAnual;
        this.dataCadastro = dataCadastro;
    }

    public Integer getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(Integer codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public BigDecimal getFaturamentoAnual() {
        return faturamentoAnual;
    }

    public void setFaturamentoAnual(BigDecimal faturamentoAnual) {
        this.faturamentoAnual = faturamentoAnual;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return nome;
    }
}
