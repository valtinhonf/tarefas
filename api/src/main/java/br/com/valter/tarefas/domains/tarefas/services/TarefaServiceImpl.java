package br.com.valter.tarefas.domains.tarefas.services;

import br.com.valter.tarefas.domains.tarefas.entities.TarefaDTO;
import br.com.valter.tarefas.domains.tarefas.entities.TarefaEntity;
import br.com.valter.tarefas.domains.tarefas.repositories.TarefaReporitory;
import br.com.valter.tarefas.utils.FuncoesBancoDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TarefaServiceImpl implements ITarefaService{

    Logger logger = LoggerFactory.getLogger(TarefaServiceImpl.class);

    @Autowired
    TarefaReporitory tarefaRepo;

    @Autowired
    FuncoesBancoDados funcoes;

    @Override
    public TarefaEntity create(TarefaDTO tarefa) {
        try{
            TarefaEntity __tarefa = new TarefaEntity(tarefa);
            __tarefa.setSequencia(funcoes.proximaSequencia());
            return tarefaRepo.save(__tarefa);
        } catch (Exception e){
            logger.error("Houve erro ao tentar salvar a tarefa! \nErro: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public TarefaEntity update(TarefaEntity tarefa) {
        try{
            return tarefaRepo.save(tarefa);
        } catch (Exception e){
            logger.error("Houve erro ao tentar atualizar a tarefa! \nErro: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<TarefaEntity> reorder(List<TarefaEntity> tarefas) {
        tarefaRepo.saveAll(tarefas);
        return this.list();
    }

    @Override
    @Transactional
    public void delete(Integer idtarefa) {
        try{
            TarefaEntity tarefa = this.get(idtarefa);
            tarefaRepo.deleteById(idtarefa);
            tarefaRepo.reorderRemaining(tarefa.getSequencia());
        } catch (Exception e){
            e.printStackTrace();
            logger.error("Houve erro ao tentar excluir a tarefa {}! \n Erro: {}", idtarefa, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<TarefaEntity> list() {
        try{
            return tarefaRepo.findAll(Sort.by(Sort.Direction.ASC, "sequencia"));
        } catch (Exception e){
            logger.error("Houve erro ao tentar listar as tarefas! \n Erro: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public TarefaEntity get(Integer idtarefa) {
        try{
            return tarefaRepo.findById(idtarefa).orElseThrow(() -> new RuntimeException("Tarefa com o id " + idtarefa + " não foi encontrada!"));
        } catch (Exception e){
            logger.error("Houve erro ao tentar listar as tarefas! \n Erro: {}", e.getMessage());
            throw e;
        }
    }
}
