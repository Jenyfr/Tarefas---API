package com.exercicio.tarefas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TarefaRequest {

    @NotBlank(message = "O titulo e obrigatorio.")
    @Size(max = 100, message = "O titulo deve ter no maximo 100 caracteres.")
    private String titulo;

    @Size(max = 255, message = "A descricao deve ter no maximo 255 caracteres.")
    private String descricao;

    private Boolean concluida;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }
}
