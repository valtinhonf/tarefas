package br.com.valter.tarefas.domains.tarefas.entities;

import br.com.valter.tarefas.utils.FuncoesBancoDados;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "tarefas")
public class TarefaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtarefa;
    @NotBlank(message = "É necessário informar um nome")
    @Column(unique = true, nullable = false)
    private String nome;

    @Min(value = 0, message = "O Custo não pode ser menor do que 0")
    private BigDecimal custo;
    @NotNull(message = "Deve ser informado uma data válida")
    private LocalDate datalimite;
    private Integer sequencia;

    public TarefaEntity() {
    }

    public TarefaEntity(TarefaDTO dto){
        this.nome = dto.getNome();
        this.custo = dto.getCusto();
        this.datalimite = dto.getDatalimite();
    }

    public TarefaEntity(Integer idtarefa, String nome, BigDecimal custo, LocalDate datalimite, Integer sequencia) {
        this.idtarefa = idtarefa;
        this.nome = nome;
        this.custo = custo;
        this.datalimite = datalimite;
        this.sequencia = sequencia;
    }

    public Integer getIdtarefa() {
        return idtarefa;
    }

    public void setIdtarefa() {
        this.idtarefa = idtarefa;
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

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

}
