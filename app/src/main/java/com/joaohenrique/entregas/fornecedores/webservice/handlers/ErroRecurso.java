package com.joaohenrique.entregas.fornecedores.webservice.handlers;

/**
 * Created by jhenr on 13/12/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class ErroRecurso {

    private Integer status;
    private String descricao;
    private List<String> mensagens;
    private Long timeStamp;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<String> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<String> mensagens) {
        this.mensagens = mensagens;
    }
}
