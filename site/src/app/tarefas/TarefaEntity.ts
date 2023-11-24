export interface TarefaEntity{
  idtarefa: number;
  nome: string;
  custo: number;
  datalimite: Date;
  sequencia: number;
  bEditing: boolean;
  bComErro: boolean;
}
