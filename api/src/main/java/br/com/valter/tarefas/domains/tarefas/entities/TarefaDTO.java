package br.com.valter.tarefas.domains.tarefas.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TarefaDTO {
    private String nome;
    private BigDecimal custo;
    private LocalDate datalimite;

    public TarefaDTO(String nome, BigDecimal custo, LocalDate datalimite) {
        this.nome = nome;
        this.custo = custo;
        this.datalimite = datalimite;
    }

    public TarefaDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public LocalDate getDatalimite() {
        return datalimite;
    }

    public void setDatalimite(LocalDate datalimite) {
        this.datalimite = datalimite;
    }
}
