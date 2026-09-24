package org.unitins.br.api;

import org.springframework.stereotype.Service;
import org.unitins.br.automato.Grade;
import org.unitins.br.automato.Populacao;
import org.unitins.br.automato.Simulacao;

@Service
public class SimulacaoService {

    private Simulacao simulacao;

    public SimulacaoService() {
        this.simulacao = criar(20, 20, 1);
    }

    public synchronized Simulacao nova(int largura, int altura, int qualidadeDeVida) {
        this.simulacao = criar(largura, altura, qualidadeDeVida);
        return simulacao;
    }

    public synchronized Simulacao obterAtual() {
        return simulacao;
    }

    public synchronized Simulacao executarCiclo() {
        simulacao.executarCiclo();
        return simulacao;
    }

    public synchronized Simulacao alterarQualidadeDeVida(int qualidadeDeVida) {
        simulacao.getPopulacao().setQualidadeDeVida(qualidadeDeVida);
        return simulacao;
    }

    private Simulacao criar(int largura, int altura, int qualidadeDeVida) {
        Grade grade = new Grade(largura, altura);
        Populacao populacao = new Populacao(qualidadeDeVida);
        return new Simulacao(grade, populacao);
    }
}
