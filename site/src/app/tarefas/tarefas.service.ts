import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TarefaEntity } from './TarefaEntity';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TarefasService {

  constructor(private http: HttpClient) { }

  novaTarefa(tarefa: TarefaEntity){
    console.log(tarefa)
    return this.http.post<TarefaEntity>(`${environment.apiurl}/`, tarefa)
  }

  listaTodas(){
    return this.http.get<TarefaEntity[]>(`${environment.apiurl}/`)
  }

  atualizaTarefa(tarefa: TarefaEntity){
    return this.http.put<TarefaEntity>(`${environment.apiurl}/`, tarefa)
  }

  buscaPorId(IdTarefa: number){
    return this.http.get<TarefaEntity>(`${environment.apiurl}/${IdTarefa}`)
  }

  deletaTarefa(idTarefa: number){
    return this.http.delete(`${environment.apiurl}/${idTarefa}`)
  }

  reordenaTarefas(tarefa1: TarefaEntity, tarefa2: TarefaEntity){
    let _Atarefas: TarefaEntity[] = [];
    _Atarefas.push(tarefa1, tarefa2)
    return this.http.post<TarefaEntity>(`${environment.apiurl}/reordena`, _Atarefas)
  }
}
