package br.com.valter.tarefas.domains.tarefas.controllers;

import br.com.valter.tarefas.domains.tarefas.entities.TarefaDTO;
import br.com.valter.tarefas.domains.tarefas.entities.TarefaEntity;
import br.com.valter.tarefas.domains.tarefas.services.TarefaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class TarefaController {

    @Autowired
    TarefaServiceImpl tarefaSrv;

    @PostMapping("/")
    public ResponseEntity<TarefaEntity> save(@RequestBody @Valid TarefaDTO tarefaDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSrv.create(tarefaDTO));
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/reordena")
    public ResponseEntity<List<TarefaEntity>> reorder(@RequestBody List<TarefaEntity> tarefas){
        try{
            return ResponseEntity.ok().body(tarefaSrv.reorder(tarefas));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<TarefaEntity> update(@RequestBody @Valid TarefaEntity tarefa){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSrv.update(tarefa));
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<TarefaEntity>> getAll(){
        try{
            return ResponseEntity.ok().body(tarefaSrv.list());
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaEntity> get(@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok().body(tarefaSrv.get(id));
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        try{
            tarefaSrv.delete(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
