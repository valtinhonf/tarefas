package br.com.valter.tarefas.domains.tarefas.repositories;

import br.com.valter.tarefas.domains.tarefas.entities.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaReporitory extends JpaRepository<TarefaEntity, Integer> {

    @Modifying
    @Query(value="update tarefas set sequencia = (sequencia -1) where sequencia > :sequencia", nativeQuery = true)
    void reorderRemaining(@Param("sequencia") Integer sequencia);

}
