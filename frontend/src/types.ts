export type EstadoCelula =
  | "ABANDONADA"
  | "BAIXA_QUALIDADE"
  | "MEDIA_QUALIDADE"
  | "ALTA_QUALIDADE";

export interface Celula {
  x: number;
  y: number;
  estado: EstadoCelula | null;
}

export interface Grade {
  largura: number;
  altura: number;
  celulas: Celula[];
}

export interface Populacao {
  qualidadeDeVida: 0 | 1 | 2;
  felicidade: number;
  populacao: number;
  taxaDeCrescimento: number;
}

export interface EstadoSimulacao {
  ciclo: number;
  populacao: Populacao;
  grade: Grade;
}
