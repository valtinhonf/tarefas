package br.com.valter.tarefas.domains.tarefas.services;

import br.com.valter.tarefas.domains.tarefas.entities.TarefaDTO;
import br.com.valter.tarefas.domains.tarefas.entities.TarefaEntity;

import java.util.List;


public interface ITarefaService {

    public TarefaEntity create(TarefaDTO tarefa);

    public TarefaEntity update(TarefaEntity tarefa);

    public List<TarefaEntity> reorder(List<TarefaEntity> tarefas);

    public void delete(Integer idtarefa);

    public List<TarefaEntity> list();

    public TarefaEntity get(Integer idtarefa);
}
