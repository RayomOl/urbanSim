import type { EstadoSimulacao } from "./types";

const BASE_URL = "/api/simulacao";

async function tratarResposta(resposta: Response): Promise<EstadoSimulacao> {
  if (!resposta.ok) {
    throw new Error(`Erro na requisição: ${resposta.status}`);
  }
  return resposta.json();
}

export const api = {
  async obterAtual(): Promise<EstadoSimulacao> {
    const resposta = await fetch(BASE_URL);
    return tratarResposta(resposta);
  },

  async nova(largura: number, altura: number, qualidadeDeVida: number): Promise<EstadoSimulacao> {
    const resposta = await fetch(`${BASE_URL}/nova`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ largura, altura, qualidadeDeVida }),
    });
    return tratarResposta(resposta);
  },

  async executarCiclo(): Promise<EstadoSimulacao> {
    const resposta = await fetch(`${BASE_URL}/ciclo`, { method: "POST" });
    return tratarResposta(resposta);
  },

  async alterarQualidadeDeVida(qualidadeDeVida: number): Promise<EstadoSimulacao> {
    const resposta = await fetch(`${BASE_URL}/qualidade-de-vida`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ qualidadeDeVida }),
    });
    return tratarResposta(resposta);
  },
};
