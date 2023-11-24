package br.com.valter.tarefas.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class FuncoesBancoDados {

    @PersistenceContext
    EntityManager em;

    public int proximaSequencia() {
        TypedQuery<Integer> query = em.createQuery("select coalesce(max(sequencia) + 1, 1) from tarefas", Integer.class);
        return query.getSingleResult();
    }

}
