import { Component, OnInit, ViewChild } from '@angular/core';
import { TarefasService } from './tarefas.service';
import { TarefaEntity } from './TarefaEntity';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.component.html',
  styleUrls: ['./tarefas.component.css']
})
export class TarefasComponent implements OnInit {
  @ViewChild('fechaBtn') fechaBtn: any;
  @ViewChild('fechaExclusaoBtn') fechaExclusaoBtn: any;

  ATarefas: TarefaEntity[] = []

  tarefaSelecionada!: TarefaEntity

  frmNovaTarefa!: FormGroup;

  constructor(private tarefaSrv: TarefasService, private fb: FormBuilder){}

  ngOnInit(): void {
    this.tarefaSrv.listaTodas().subscribe(tarefas => {this.ATarefas = tarefas; console.log(this.ATarefas)});
    console.log(this.ATarefas)
    this.frmNovaTarefa = this.fb.group({
      nome: [null],
      custo: [0, Validators.min(0)],
      datalimite: [null],
      editing: [false]
    })
  }

  salvaNovaTarefa(){
    this.tarefaSrv.novaTarefa(this.frmNovaTarefa.value).subscribe(novaTarefa => {
      this.ATarefas.push(novaTarefa);
      this.fechaBtn.nativeElement.click();
    })
  }

  atualiza(tarefa: TarefaEntity){
    if (tarefa){
      //validando requisitos
      if (tarefa.custo > 0?? this.temTarefaComMesmoNome(tarefa) == false){
          this.tarefaSrv.atualizaTarefa(tarefa).subscribe(res => {
            this.ATarefas.splice(
              this.ATarefas.findIndex(_tarefa => _tarefa.idtarefa === tarefa.idtarefa), 1, res)
            })
        } else {
          tarefa.bComErro = true;
        }
      }
    }

  cancela(tarefa: TarefaEntity){
    this.tarefaSrv.buscaPorId(tarefa.idtarefa).subscribe(resTarefa => {
                                                            this.ATarefas.splice(
                                                              this.ATarefas.findIndex(_tarefa => _tarefa.idtarefa === tarefa.idtarefa), 1, resTarefa)
                                                            })

  }

  up(tarefa: TarefaEntity){
    let indice = this.ATarefas.findIndex(_tarefa => _tarefa.idtarefa == tarefa.idtarefa);
    tarefa.sequencia--;
    let tarefaDeCima = this.ATarefas[indice - 1];
    tarefaDeCima.sequencia++;

    this.ATarefas.splice(indice-1, 1, tarefa)
    this.ATarefas.splice(indice, 1, tarefaDeCima)
    this.tarefaSrv.reordenaTarefas(tarefa, tarefaDeCima).subscribe(() => {});
  }

  down(tarefa: TarefaEntity){
    let indice = this.ATarefas.findIndex(_tarefa => _tarefa.idtarefa == tarefa.idtarefa);
    tarefa.sequencia++;
    let tarefaDeBaixo = this.ATarefas[indice + 1];
    tarefaDeBaixo.sequencia--;

    this.ATarefas.splice(indice, 1, tarefaDeBaixo)
    this.ATarefas.splice(indice+1, 1, tarefa)
    this.tarefaSrv.reordenaTarefas(tarefa, tarefaDeBaixo).subscribe(() => {});
  }

  deletaTarefa(tarefa: TarefaEntity){
    const indice = this.ATarefas.indexOf(tarefa);
    this.ATarefas.splice(indice, 1);
    this.fechaExclusaoBtn.nativeElement.click();
    this.tarefaSrv.deletaTarefa(this.tarefaSelecionada.idtarefa).subscribe(() => {})

  }

  temTarefaComMesmoNome(tarefaParaValidar: TarefaEntity){
    const _tarefaEncontrada = this.ATarefas.find(_tarefa => (_tarefa.nome == tarefaParaValidar.nome && _tarefa.idtarefa != tarefaParaValidar.idtarefa));
    if (_tarefaEncontrada){
      return true
    } else {
      return false;
    }
  }

}
