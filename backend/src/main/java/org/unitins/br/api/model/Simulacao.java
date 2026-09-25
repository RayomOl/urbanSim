package org.unitins.br.api.model;

import java.util.List;

public class Simulacao {

    private final Cidade cidade;
    private final Populacao populacao;
    private final Regras regras;
    private int ciclo;

    public Simulacao(int largura, int altura, int qualidadeDeVida) {
        this.cidade = new Cidade(largura, altura);
        this.populacao = new Populacao(qualidadeDeVida);
        this.regras = new Regras();
        this.ciclo = 0;
    }

    public void executarCiclo() {
        // 1. Atualiza felicidade e taxa de crescimento da população
        populacao.setFelicidade(regras.calcularFelicidade(populacao));
        populacao.setTaxaCrescimento(regras.calcularTaxaCrescimento(populacao));

        int largura = cidade.getLargura();
        int altura = cidade.getAltura();
        Estado[][] proximoEstado = new Estado[largura][altura];
        int contagemPopulacao = 0;

        // 2. Calcula os próximos estados sem alterar o grid atual
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                Construcao atual = cidade.getPosConstrucao(x, y);
                List<Construcao> vizinhos = cidade.getVizinhos(x, y);
                proximoEstado[x][y] = regras.calcularProximoEstado(atual, vizinhos, populacao);

                if (proximoEstado[x][y] != null) {
                    contagemPopulacao++;
                }
            }
        }

        // 3. Aplica todos os estados simultaneamente
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                cidade.getPosConstrucao(x, y).setEstado(proximoEstado[x][y]);
            }
        }

        populacao.setPopulacao(contagemPopulacao);
        ciclo++;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Populacao getPopulacao() {
        return populacao;
    }

    public int getCiclo() {
        return ciclo;
    }

}